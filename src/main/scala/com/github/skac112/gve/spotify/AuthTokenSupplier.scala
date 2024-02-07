package com.github.skac112.gve.spotify

import com.github.skac112.gve.Main.{client_id, client_secret}
import com.github.skac112.gve.SpotifyAuthResp
import org.scalajs.dom.ext.Ajax
import upickle.default.read

import java.util.Base64
import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global

case class AuthTokenSupplier(clientId: String, clintSecret: String) {
  case class TokenData(token: String, acquireTime: Long, validity: Long)
  val base64Enc = Base64.getEncoder
  val authUrl = "https://accounts.spotify.com/api/token"
  val authHeaderValue = s"Basic ${base64Enc.encodeToString(s"$client_id:$client_secret".getBytes)}"

  val headers = Map("Authorization" -> authHeaderValue,
    "Content-Type" -> "application/x-www-form-urlencoded")

  var authRequestData = "grant_type=client_credentials"

  @volatile var currentTokenDataO: Option[Future[TokenData]] = None

  def token: Future[String] = {
    currentTokenDataO match {
      case Some(token_data_f) => token_data_f foreach { token_data: TokenData =>
        if (token_data.acquireTime + token_data.validity - 10000L < System.currentTimeMillis) {
            acquireNewTokenData()
        }
      }
      case None => acquireNewTokenData()
    }

    currentTokenDataO.get map { token_data => token_data.token }
  }

  def acquireNewTokenData() = {
    val new_token_data = Ajax.post(authUrl, authRequestData, 10000, headers, true) map { case xhr =>
      val resp_text = xhr.responseText
      val res = read[SpotifyAuthResp](resp_text)
      TokenData(res.access_token, System.currentTimeMillis(), res.expires_in*1000)
    }
    currentTokenDataO = Some(new_token_data)
  }
}


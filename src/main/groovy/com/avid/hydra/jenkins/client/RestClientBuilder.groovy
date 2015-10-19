package com.avid.hydra.jenkins.client

import groovyx.net.http.RESTClient

/**
 * Created by andrii.ievdokimov on 9/28/2015.
 */
class RestClientBuilder {

  private URL base
  private String userName
  private String password

  RestClientBuilder(String base, String userName, String password) {
    this(base)
    this.userName = userName
    this.password = password
  }

  RestClientBuilder(String base) {
    this.base = new URL(base)
  }

  RESTClient build() {
    RESTClient client = new RESTClient(base.toString())
    if (userName && password)
      client.auth.basic(userName, password)
    client
  }

}

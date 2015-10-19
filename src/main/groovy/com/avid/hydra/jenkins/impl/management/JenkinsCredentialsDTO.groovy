package com.avid.hydra.jenkins.impl.management

import com.avid.hydra.jenkins.api.management.JenkinsCredentials

/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */
class JenkinsCredentialsDTO implements JenkinsCredentials{

  String name
  String userName
  String userPassword
  String URL
}

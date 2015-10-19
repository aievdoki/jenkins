package com.avid.hydra.jenkins.api

/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */
interface JenkinsHealthReport {

  String getDescription()

  String getIconClassName()

  String getIconUrl()

  int getScore()

}
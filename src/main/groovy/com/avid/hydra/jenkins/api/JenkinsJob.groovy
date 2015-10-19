package com.avid.hydra.jenkins.api

/**
 * Created by andrii.ievdokimov on 9/28/2015.
 */
interface JenkinsJob {

  String getDisplayName()

  String getName()

  String getUrl()

  boolean isBuildable()

  JenkinsBuildInfo getLastBuild()

  JenkinsBuildInfo getLastSuccessfulBuild()

  JenkinsBuildInfo getLastUnsuccessfulBuild()

  int getNextBuildNumber()

  String getBuildLink()

}

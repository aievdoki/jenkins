package com.avid.hydra.jenkins.api

/**
 * Created by andrii.ievdokimov on 9/28/2015.
 */
interface JenkinsBuildInfo {

  int getNumber()

  String getURL()

  long getDuration()

  long getTimestamp()

  long getEstimatedDuration()

  boolean isBuilding()

  String getStatus()

}
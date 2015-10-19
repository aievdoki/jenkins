package com.avid.hydra.jenkins.api.management

import com.avid.hydra.jenkins.impl.management.JenkinsCredentialsDTO

/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */
interface User {

  void saveJenkinsCredentials(JenkinsCredentialsDTO credentials)

  void saveFewJenkinsCredentials(List<JenkinsCredentialsDTO> credentials)

  Map<String, JenkinsCredentialsDTO> getJenkinsCredentials()

  JenkinsCredentialsDTO getJenkinsCredentials(String jenkinsName)


  Map<List, JenkinsHelper> getJenkinsHelpers()

  JenkinsHelper getJenkinsHelperByName(String name)

  Map<String, List<String>> getSavedJenkinsJobs()

  List<String> getSavedJenkinsJobsByJenkinsName(String jenkinsName)

  void saveJenkinsJob(String jenkinsName, String job)

  void saveJenkinsJobs(String jenkinsName, List<String> job)


}

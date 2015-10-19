package com.avid.hydra.jenkins.impl.management

import com.avid.hydra.jenkins.api.management.JenkinsHelper
import com.avid.hydra.jenkins.api.management.User
/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */
class ApplicationUser implements User {

  private final Map<String, JenkinsCredentialsDTO> jenkinsCredentials = new HashMap<String, JenkinsCredentialsDTO>()

  private final Map<List, JenkinsHelperImpl> jenkinsHelpers = new HashMap<String, JenkinsHelperImpl>()

  private final Map<String, List<String>> savedJenkinsJobs = new HashMap<String, List<String>>()

  Map<String, JenkinsCredentialsDTO> getJenkinsCredentials(){
    jenkinsCredentials
  }

  JenkinsCredentialsDTO getJenkinsCredentials(String jenkinsName){
    jenkinsCredentials[jenkinsName]
  }

  @Override
  Map<List, JenkinsHelper> getJenkinsHelpers() {
    jenkinsHelpers
  }

  @Override
  JenkinsHelper getJenkinsHelperByName(String name) {
    jenkinsHelpers[name]
  }

  @Override
  void saveJenkinsCredentials(JenkinsCredentialsDTO credentials) {
    jenkinsCredentials.put(credentials.name, credentials)
    jenkinsHelpers.put(credentials.name, buildJenkinsManager(credentials))
  }

  @Override
  void saveFewJenkinsCredentials(List<JenkinsCredentialsDTO> credentials) {
    credentials.each {
      jenkinsCredentials.put(it.name, it)
      jenkinsHelpers.put(it.name, buildJenkinsManager(credentials))
    }
  }

  private JenkinsHelper buildJenkinsManager(JenkinsCredentialsDTO jenkinsCredentials) {
    if ((jenkinsCredentials.userName == "") || (jenkinsCredentials.userPassword == ""))
      return new JenkinsHelperImpl(jenkinsCredentials.URL)
    else
      return new JenkinsHelperImpl(jenkinsCredentials.URL, jenkinsCredentials.userName, jenkinsCredentials.userPassword)
  }


  @Override
  Map<String, List<String>> getSavedJenkinsJobs() {
    savedJenkinsJobs
  }

  @Override
  List<String> getSavedJenkinsJobsByJenkinsName(String jenkinsName) {
    savedJenkinsJobs.jenkinsName
  }

  @Override
  void saveJenkinsJob(String jenkinsName, String job) {
    if (savedJenkinsJobs.containsKey(jenkinsName))
      savedJenkinsJobs[jenkinsName].add(job)
    else
      savedJenkinsJobs.put(jenkinsName, [job])
  }

  @Override
  void saveJenkinsJobs(String jenkinsName, List<String> jobs) {
    if (savedJenkinsJobs.containsKey(jenkinsName))
      savedJenkinsJobs[jenkinsName].addAll(jobs)
    else
      savedJenkinsJobs.put(jenkinsName, jobs)
  }

}

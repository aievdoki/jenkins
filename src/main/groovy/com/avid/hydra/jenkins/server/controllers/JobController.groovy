package com.avid.hydra.jenkins.server.controllers

import com.avid.hydra.jenkins.api.management.User
import com.avid.hydra.jenkins.dto.JenkinsJobDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */
@RestController
@RequestMapping(value = "/job")
class JobController {

  @Autowired
  @Qualifier("loggedUser")
  User user

  @RequestMapping(value = "/details", method = GET)
  JenkinsJobDTO getJobByName(@RequestParam(value = "jenkinsName") String jenkinsName,
                             @RequestParam(value = "jobName") String jobName) {
    user.getJenkinsHelperByName(jenkinsName).getJobByName(jobName)
  }

  @RequestMapping(value = "/all", method = GET)
  List<String> allJobDetails(@RequestParam(value = "jenkinsName") String jenkinsName) {
      user.getJenkinsHelperByName(jenkinsName).getAllJobs()
  }

  @RequestMapping(value = "/save", method = POST)
  void saveJob(@RequestBody Map<String, List<String>> jobs) {
    jobs.each { k, v ->
      user.saveJenkinsJobs(k, v)
    }

  }
  @RequestMapping(value = "/names", method = GET)
  List<String> allJobDetails() {
      [new JobID("core-deprecate-build-scripts-heartbeat"),new JobID("core-master-jedi-pirates-testem-jsunit"),new JobID("core-master-apps_core_api"),new JobID("core-master-api-local"),new JobID("core-master-api-ums"),new JobID("name j2"),new JobID("temp"),new JobID("avid"),new JobID("avid 34")]
  }

  @RequestMapping(value = "/find", method = GET)
  def jobsFind(@RequestParam(value = "s") String s,
               @RequestParam(value = "jenkinsName") String jenkinsName) {
      def array = user.getJenkinsHelperByName(jenkinsName).getAllJobs()
      array.findAll {it -> it.startsWith(s)}.collect {it -> [name: it]}
  }
}

class JobID{
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public JobID(){}

    public JobID(String name){
        this.name = name;
    }
}
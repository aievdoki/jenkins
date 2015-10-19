package com.avid.hydra.jenkins.impl.management

import com.avid.hydra.jenkins.api.management.User
import com.avid.hydra.jenkins.api.management.UserManager

/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */

class UserManagerImpl implements UserManager {

  HashMap<String, User> users = new HashMap<String, User>()

  UserManagerImpl() {
    users.put("gromit", new ApplicationUser())
  }


  @Override
  User getUser(String name) {
    users.gromit
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.User;

/**
 *
 * @author cneree
 */
public class UserService extends AbstractFacade<User> {

    public UserService() {
        super(User.class);
    }
    public void initDb(){
        for (int i = 0; i < 5; i++) {
           User user = new User() ;
           user.setId(1+i);
            create(user);
        }
    }

    public int seConnecter(User user) {
        Integer id =findUser(user.getEmail());
        User LoadedUser = find(id);
        if (LoadedUser == null) {
            return -1;
        } else if (!LoadedUser.getPassword().equals(user.getPassword())) {
            return -2;
        } else {
            return 1;
        }
    }

    public int createUser(User user) {
        user.setId(user.getId());
        user.setEmail(user.getEmail());
        user.setNom(user.getNom());
        user.setPrenom(user.getPrenom());
        user.setPassword(user.getPassword());
        create(user);
        return 1;
    }

    public String findUsername(String email) {
        String query = "SELECT u.nom FROM User u WHERE u.email='" + email + "'";
        return (String) getEntityManager().createQuery(query).getSingleResult();
    }
public Integer findUser(String email){
    String query="SELECT u.id FROM User u WHERE u.email='"+email +"'";
            return (Integer) getEntityManager().createQuery(query).getSingleResult();

}

    public String findGenre(String email) {
        String query = "SELECT u.genre FROM User u WHERE u.email='" + email + "'";
        return (String) getEntityManager().createQuery(query).getSingleResult();
    }
}
package ua.te.hackathon.smartcity2015;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.Assert;

import io.realm.Realm;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    public void testRealm() {
        Realm realm = Realm.getInstance(getContext());
        User user = new User();
        user.setAvatarResourcesUrl("url://test/avatar.png");
        user.setName("Lol");
        user.setSurname("Lol");
        user.setNickname("Lol");
        user.setPhone("+380972393347");
        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();

        User result = realm.where(User.class).findFirst();

        Assert.assertTrue("Lol".equals(result.getNickname()));
    }
}
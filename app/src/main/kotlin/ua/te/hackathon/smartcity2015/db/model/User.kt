package ua.te.hackathon.smartcity2015.db.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by nk91 on 13.02.16.
 */
@RealmClass
public open class User : RealmObject() {

  @PrimaryKey
  public open var nickname: String? = null

  public open var name: String? = null

  public open var surname: String? = null

  public open var avatarResourcesUrl: String? = null

  public open var phone: String? = null

}

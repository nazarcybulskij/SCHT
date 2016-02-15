package ua.te.hackathon.smartcity2015.db.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by nk91 on 13.02.16.
 */
@RealmClass
public open class Event : RealmObject() {

  @PrimaryKey
  public open var id: Int = 0

  public open var name: String? = null

  @Index
  public open var date: Long = 0

  public open var description: String? = null

  public open var backgroundUrl: String? = null

  @Index
  public open var place: String? = null

  public open var joinedUsers: RealmList<User>? = null

}

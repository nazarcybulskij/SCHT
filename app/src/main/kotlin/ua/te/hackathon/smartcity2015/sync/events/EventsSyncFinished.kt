package ua.te.hackathon.smartcity2015.sync.events

/**
 * @author victor
 * *
 * @since 2016-02-14
 */
class EventsSyncFinished {

  val isSuccess: Boolean
  val error: Throwable?

  constructor() {
    isSuccess = true
    error = null
  }

  constructor(error: Throwable) {
    isSuccess = false
    this.error = error
  }
}

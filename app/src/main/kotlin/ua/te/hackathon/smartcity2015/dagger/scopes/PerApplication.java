package ua.te.hackathon.smartcity2015.dagger.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author victor
 * @since 2015-12-30
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApplication {
}

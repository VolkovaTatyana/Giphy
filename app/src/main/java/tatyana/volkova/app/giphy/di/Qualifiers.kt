package tatyana.volkova.app.giphy.di

import javax.inject.Qualifier


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Remote

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Device
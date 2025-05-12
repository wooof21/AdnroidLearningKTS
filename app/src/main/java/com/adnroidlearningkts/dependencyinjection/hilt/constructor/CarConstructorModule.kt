package com.adnroidlearningkts.dependencyinjection.hilt.constructor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt Module: defines how dependencies are provided
 *      * are used to provide instances of types that cannot be constructor-injected.
 *      This is typically necessary for interfaces, classes from external libraries,
 *      or classes where need custom creation logic.
 *
 * @InstallIn(SingletonComponent::class): This annotation specifies which Hilt component the
 *  module should be installed in. A Hilt component is a container for dependencies,
 *  and it defines the scope and lifetime of the dependencies it provides.
 *      * SingletonComponent::class refers to the component that lives for the entire application's lifecycle.
 *      Dependencies provided within a module installed in SingletonComponent will be singletons,
 *      meaning there will be only one instance of that dependency throughout the app's lifetime.
 *
 *      *
 *      Modules installed in `SingletonComponent` provide application-wide singletons.
 *      Modules installed in `ActivityRetainedComponent` provide dependencies that survive configuration changes.
 *      Modules installed in `ViewModelComponent` provide dependencies scoped to a specific ViewModel.
 *
 *      `ActivityComponent` (Activity level)
 *      `FragmentComponent` (Fragment level)
 *      `ViewComponent` (View level)
 *      `ServiceComponent` (Service level)
 *      `WorkerComponent` (Worker level
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object CarConstructorModule {

    /**
     * tells Hilt how to provide an object
     * @Provides: This annotation signals to the Hilt that this function
     * is responsible for providing an instance of a particular type.(When Constructor Injection is not sufficient or possible)
     * When Hilt needs an EngineHilt object, it will call this function.
     *
     * @Singleton: Ensures that Hilt provides a single instance of the dependency across the
     * entire app
     *
     * For the CarHilt and EngineHilt class, the Provides funs are not needed, since they both have
     * `@Inject constructor()`, and Hilt knows how to create an instance on its own
     *
     * The @Provides fun is only required if want to control the scope of CarHilt to be a @Singleton within the SingletonComponent
     *      * @Inject constructor(), Hilt will create a new instance of that class every time
     *      it's requested by default (unless the requesting site has a specific scope annotation like @Singleton).
     *
     * Generally need a Hilt Module and @Provides functions when:
     *      * Providing instances of interfaces or classes from external libraries: These don't have @Inject constructors to annotate.
     *      * Providing instances of classes that require complex setup or configuration: that might need to build an object step-by-step.
     *      * Controlling the scope of an injectable type: When want to ensure a specific instance is
     *          reused within a certain component's lifecycle (like making it a @Singleton, @ActivityRetainedScoped, etc.).
     */
    @Provides
    @Singleton
    fun provideEngine() = EngineConstructor()

    @Provides
    @Singleton
    fun provideCar(engine: EngineConstructor): CarConstructor {
        return CarConstructor(engine)
    }

}
package com.adnroidlearningkts.paging.model.pojo

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.adnroidlearningkts.BR
import com.adnroidlearningkts.R
import com.bumptech.glide.Glide
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PMovie(@SerializedName("adult")
             @Expose
             var adult: Boolean? = null,
             @SerializedName("backdrop_path")
             @Expose
             var backdropPath: String? = null,
             @SerializedName("genre_ids")
             @Expose
             var genreIds: List<Int>? = null,
             @SerializedName("id")
             @Expose
             var id: Int? = null,
             @SerializedName("original_language")
             @Expose
             var originalLanguage: String? = null,
             @SerializedName("original_title")
             @Expose
             var originalTitle: String? = null,
             @SerializedName("overview")
             @Expose
             var overview: String? = null,
             @SerializedName("popularity")
             @Expose
             var popularity: Double? = null,
             /**
              * To display an img with Glide <base_url: https:></base_url:>//image.tmdb.org/t/p/w500/> + <posterPath>
              * use @BindingAdapter
             </posterPath> */
             @SerializedName("poster_path") @Expose
             val posterPath: String? = null,
             @SerializedName("release_date")
             @Expose
             val releaseDate: String? = null,
             @SerializedName("video")
             @Expose
             var video: Boolean? = null,
             @SerializedName("vote_count")
             @Expose
             var voteCount: Int? = null
): BaseObservable() {

    /**
     * in order to tell Android studio that the TextView is linked to this variable
     * use @Bindable to getters and add notify property change in setters
     *
     * BR: stand for Binding Resources
     *      - an automatically generated class by Android Data Binding library, it contains integer constants
     *      that represent the binding expressions used in the layout files
     *
     *      Any UI elements bound to this property will automatically update to reflect the new value
     */
    @SerializedName("title")
    @Expose
    @get:Bindable // Apply @Bindable to the getter
    var title: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @SerializedName("vote_average")
    @Expose
    @get:Bindable
    var voteAverage:Double? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.voteAverage)
        }


    /**
     * @BindingAdapter is applied to a function that will perform the custom binding logic.
     * This function must be:
     *      * Static: In Kotlin, this means it should be defined as a top-level function or
     *          within an object or companion object. Top-level functions are often preferred for
     *          binding adapters as they don't require an object instance.
     *      * Public: The function needs to be publicly accessible.
     *
     * The @BindingAdapter annotation takes one or more attribute names as arguments.
     * These are the custom attributes will use in the layout XML.
     *
     * If the binding adapter handles multiple attributes, can list them in the array:
     *      @BindingAdapter("attribute1", "attribute2")
     *      fun binding_fun_name(...) {
     *          // Binding logic for multiple attributes
     *      }
     *
     * Function Parameters:
     *      * First Parameter: The first parameter is always the View type that the binding adapter
     *          is intended for (e.g., ImageView, TextView, RecyclerView).
     *      * Subsequent Parameters: Each subsequent parameter corresponds to an attribute name
     *          listed in the @BindingAdapter annotation, in the same order.
     *          The type of the parameter should match the expected type of the data being bound
     *          to that attribute. Nullable types are common if the bound data can be null.
     *
     * To Use the Custom Attribute in the Layout XML:
     *      * use the custom attribute name defined in the @BindingAdapter annotation
     *      <ImageView
     *          android:layout_width="wrap_content"
     *          android:layout_height="wrap_content"
     *          app:imageUrl="@{viewModel.posterPath}" />
     *
     *  Handling Multiple Attributes:
     *
     *      @BindingAdapter("android:paddingLeft", "android:paddingRight", requireAll = false)
     *      fun setPadding(view: View, paddingLeft: Float, paddingRight: Float) {
     *          view.setPadding(paddingLeft.toInt(), view.paddingTop, paddingRight.toInt(), view.paddingBottom)
     *      }
     *
     *  Passing Listeners: can also use @BindingAdapter to set event listeners.
     *
     *      @BindingAdapter("android:onClick")
     *      fun setOnClickListener(view: View, listener: View.OnClickListener?) {
     *          view.setOnClickListener(listener)
     *      }
     *
     *      // Or with a lambda (more common in Kotlin)
     *      @BindingAdapter("onDebouncedClick")
     *      fun setDebouncedClickListener(view: View, clickListener: (() -> Unit)?) {
     *          val debouncedClickListener = object : View.OnClickListener {
     *              // Implement debouncing logic here
     *              override fun onClick(v: View?) {
     *                  clickListener?.invoke()
     *              }
     *          }
     *          view.setOnClickListener(debouncedClickListener)
     *      }
     *
     *
     * When Exception: java.lang.IllegalStateException: Required DataBindingComponent is null in class MovieListItemBindingImpl.
     *
     * Required DataBindingComponent is null: Data Binding sometimes requires an object called a
     * DataBindingComponent to be present. This component is used to provide instances of objects needed by BindingAdapters.
     *
     * When use BindingAdapter, set the method as static using @JvmStatic
     */
    companion object {
        @JvmStatic
        @BindingAdapter("posterPath")
        fun loadImage(iv: ImageView, path: String?) {
            val baseImgUrl = "https://image.tmdb.org/t/p/w500/"
            val imgUrl = baseImgUrl + path
            Glide.with(iv.context)
                .load(imgUrl)
                .into(iv)
        }
    }

}
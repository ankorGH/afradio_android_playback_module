package com.alttech.afrsdk.network

import com.alttech.afrsdk.data.LoadMoreDataResult
import com.alttech.afrsdk.data.WidgetDataResult
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by bubu on 26/10/2017.
 */

object ApiCalls {

  private val apiService: ApiService = ApiService.getInstance()

  fun getShows(appId: String, resId: String) =
      apiService.getShows(appId, resId).compose(applySchedulers<WidgetDataResult>())

  fun loadMore(appId: String, showId: String, offset:Int, limit:Int ) =
      apiService.loadMore(appId, showId, offset, limit).compose(applySchedulers<LoadMoreDataResult>())


  private fun <T> applySchedulers(): Observable.Transformer<T, T> {
    return Observable.Transformer { observable ->
      observable.subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
    }
  }
}

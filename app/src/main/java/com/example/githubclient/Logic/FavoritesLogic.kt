package com.example.githubclient.Logic

import android.content.Context
import com.example.githubclient.Models.RealmRepository
import com.example.githubclient.Models.Repository
import io.realm.Realm
import io.realm.RealmConfiguration

class FavoritesLogic {

    companion object {

        fun addToFavorites(repos: Repository, context: Context) {
            initRealm(context)
            val realm = Realm.getDefaultInstance()
            val currentLogin = AuthorizationLogic.getSavedCreds(context).first
            val duplicate = realm.where(RealmRepository::class.java)
                .equalTo("id", repos.id)
                .equalTo("userLogin", currentLogin)
                .findFirst()
            if (duplicate != null) {
                realm.close()
                return
            }
            var realmRepos = RealmRepository()
            realmRepos = realmRepos.apply {
                id = repos.id
                name = repos.name.toString()
                description = repos.description.toString()
                language = repos.language.toString()
                forksCount = repos.forksCount
                stargazersCount = repos.stargazersCount
                ownerLogin = repos.ownerLogin.toString()
                ownerAvatarUrl = repos.ownerAvatarUrl.toString()
                commitsUrl = repos.commitsUrl.toString()
                userLogin = currentLogin
            }
            realm.beginTransaction()
            realm.copyToRealm(realmRepos)
            realm.commitTransaction()
            realm.close()
        }

        fun getFavoritesList(context: Context): List<Repository> {
            initRealm(context)
            val realm = Realm.getDefaultInstance()
            val currentLogin = AuthorizationLogic.getSavedCreds(context).first
            val result = arrayListOf<Repository>()
            val query = realm
                .where(RealmRepository::class.java)
                .equalTo("userLogin", currentLogin)
                .findAll()
            for (i in query) {
                val repos = Repository(
                    i.id,
                    i.name,
                    i.description,
                    i.language,
                    i.forksCount,
                    i.stargazersCount,
                    i.ownerLogin,
                    i.ownerAvatarUrl,
                    i.commitsUrl
                )
                result.add(repos)
            }
            realm.close()
            return result
        }

        fun removeFromFavorites(id: Int, context: Context) {
            initRealm(context)
            val realm = Realm.getDefaultInstance()
            val currentLogin = AuthorizationLogic.getSavedCreds(context).first
            realm.beginTransaction()
            realm.where(RealmRepository::class.java)
                .equalTo("id", id)
                .equalTo("userLogin", currentLogin)
                .findFirst()?.deleteFromRealm()
            realm.commitTransaction()
            realm.close()
        }

        fun isInFavorites(id: Int, context: Context): Boolean {
            initRealm(context)
            val realm = Realm.getDefaultInstance()
            val currentLogin = AuthorizationLogic.getSavedCreds(context).first
            val query = realm.where(RealmRepository::class.java)
                .equalTo("id", id)
                .equalTo("userLogin", currentLogin)
                .findFirst()
            val result = (query != null)
            realm.close()
            return result
        }

        private fun initRealm(context: Context) {
            Realm.init(context)
            val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
            Realm.setDefaultConfiguration(config)
        }
    }
}
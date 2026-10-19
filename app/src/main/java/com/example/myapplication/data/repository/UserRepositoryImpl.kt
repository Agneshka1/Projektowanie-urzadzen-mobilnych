package com.example.myapplication.data.repository

import com.example.myapplication.data.local.UserDao
import com.example.myapplication.data.local.UserEntity
import com.example.myapplication.data.remote.ApiService
import com.example.myapplication.data.remote.toDomain
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val apiService: ApiService
) : UserRepository {

    override fun getUsers(): Flow<List<User>> {
        return userDao.getUsers().map { entities ->
            entities.map { User(id = it.id, name = it.name, email = it.email) }
        }
    }

    override suspend fun refreshUsers() {
        val remoteUsers = apiService.getUsers()
        userDao.insertUsers(remoteUsers.map { UserEntity(id = it.id, name = it.name, email = it.email) })
    }
}

package com.mimoza_app.notes.shoppinglist_room.utilits

import com.mimoza_app.notes.shoppinglist_room.MainActivity
import com.mimoza_app.notes.shoppinglist_room.database.DatabaseRepository

lateinit var APP_ACTIVITY:MainActivity
lateinit var REPOSITORY: DatabaseRepository
lateinit var EMAIL:String
lateinit var PASSWORD:String
lateinit var CURRENT_ID:String
const val TYPE_FIREBASE = "type_firebase"
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_LAUNCH =  "type_launch"
const val ADD_LAUNCH = "add_launch"
const val EDIT_LAUNCH = "edit_launch"
const val KEY_CLICK_ITEM = "ITEM"
const val ID_FIREBASE = "idFirebase"
const val NAME = "name"
const val TEXT = "text"
package br.com.zenith.oceantechapp.model

import java.io.Serializable

class User: Serializable {
    private var email: String  get() {
            return email
        }
    private var uid: String get() {
            return uid
        }

    constructor(email: String, uid: String) {
        this.email = email
        this.uid = uid
    }
}
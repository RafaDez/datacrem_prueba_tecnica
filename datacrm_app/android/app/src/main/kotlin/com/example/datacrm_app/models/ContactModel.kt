package com.example.datacrm_app.models

import kotlinx.serialization.Serializable

class ContactModel {
    @Serializable
    data class ApiResponseContact(
        val success: Boolean,
        val result: List<Contact>
    )

    @Serializable
    data class Contact(
        val salutationtype: String,
        val firstname: String,
        val contact_no: String,
        val phone: String,
        val lastname: String,
        val mobile: String,
        val account_id: String,
        val homephone: String,
        val leadsource: String,
        val otherphone: String,
        val title: String,
        val fax: String,
        val department: String,
        val birthday: String,
        val email: String,
        val contact_id: String,
        val assistant: String,
        val secondaryemail: String,
        val assistantphone: String,
        val donotcall: String,
        val emailoptout: String,
        val assigned_user_id: String,
        val reference: String,
        val notify_owner: String,
        val createdtime: String,
        val modifiedtime: String,
        val modifiedby: String,
        val mailingstreet: String,
        val otherstreet: String,
        val mailingcity: String,
        val othercity: String,
        val mailingstate: String,
        val otherstate: String,
        val mailingzip: String,
        val otherzip: String,
        val othercountry: String,
        val mailingpobox: String,
        val otherpobox: String,
        val description: String,
        val isconvertedfromlead: String,
        val createdby: String,
        val filter: String,
        val rdstationid: String,
        val check_birthday: String,
        val mailingcountry: String,
        val public_url_rd: String,
        val origin_creation_contact_pick: String,
        val cf_1037: String,
        val cf_1039: String,
        val social_network_facebook_id: String,
        val social_network_instagram_id: String,
        val id: String
    )
}
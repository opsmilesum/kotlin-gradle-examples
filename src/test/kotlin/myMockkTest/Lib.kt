package myMockkTest

interface AddressBook {
    val contacts: List<Contact>
}

interface Contact {
    val name: String
    val address: Address
}

interface Address {
    val city: String
}
class Cities{
    var name: String? = ""
    var state: String? = ""
    var areaCode: String? = ""

    override fun toString(): String {
        return "$name|$state|$areaCode\n"
    }
}
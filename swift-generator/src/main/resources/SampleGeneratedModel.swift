// Based on the reference api model:
// https://github.com/mbryzek/apidoc-generator/blob/master/reference-api/service.json

enum AgeGroup {
    case Youth, Adult
}

class Big {
    var f1: String
    var f2: String
    var f3: String
    var f4: String
    var f5: String
    var f6: String
    var f7: String
    var f8: String
    var f9: String
    var f10: String
    var f11: String
    var f12: String
    var f13: String
    var f14: String
    var f15: String
    var f16: String
    var f17: String
    var f18: String
    var f19: String
    var f20: String
    var f21: String
    
    init(f1:String, f2: String, f3: String, f4: String, f5: String,
        f6: String, f7: String, f8:String, f9: String, f10: String,
        f11: String, f12: String, f13: String, f14: String, f15: String,
        f16: String, f17: String, f18: String, f19: String, f20: String,
        f21: String) {
            self.f1 = f1
            self.f2 = f2
            self.f3 = f3
            self.f4 = f4
            self.f5 = f5
            self.f6 = f6
            self.f7 = f7
            self.f8 = f8
            self.f9 = f9
            self.f10 = f10
            self.f11 = f11
            self.f12 = f12
            self.f13 = f13
            self.f14 = f14
            self.f15 = f15
            self.f16 = f16
            self.f17 = f17
            self.f18 = f18
            self.f19 = f19
            self.f20 = f20
            self.f21 = f21
    }
}

class Echo {
    var value: String
    
    init(value: String) {
        self.value = value
    }
}

class Error {
    var code: String
    var message: String
    
    init(code: String, message: String) {
        self.code = code
        self.message = message
    }
}

class Organization {
    var guid: NSUUID
    var name: String
    
    init(guid: NSUUID, name: String) {
        self.guid = guid
        self.name = name
    }
}

class User {
    var guid: NSUUID
    var email: String
    var active: Boolean
    var ageGroup: AgeGroup
    var tags: [String: String]?
    
    init(guid: NSUUID, email: String, active: Boolean, ageGroup: AgeGroup, tags: [String: String]? = nil) {
        self.guid = guid
        self.email = email
        self.active = active
        self.ageGroup = ageGroup
        self.tags = tags
    }
}

class Member {
    var guid: NSUUID
    var organization: Organization
    
    init(guid: NSUUID, organization: Organization) {
        self.guid = guid
        self.organization = organization
    }
}

class Group {
    var members: [User]
    
    init(members: [User]) {
        self.members = members
    }
}

class UserList {
    var users: [User]
    
    init(users: [User]) {
        self.users = users
    }
}

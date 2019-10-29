struct Keys {
    static let IP_ADDRESS_ST0RAGE = Storage(key: "ip_address", defaultValue: "192.168.4.1")
    static let PORT_STORAGE = Storage(key: "port", defaultValue: "8080")

    static let MOVE_FORWARD_STORAGE = Storage(key: "move_forward", defaultValue: "F")
    static let MOVE_BACKWARD_STORAGE = Storage(key: "move_backward", defaultValue: "B")
    static let MOVE_STOP_STORAGE = Storage(key: "move_stop", defaultValue: "S")
    static let TURN_RIGHT_STORAGE = Storage(key: "turn_right", defaultValue: "R")
    static let TURN_LEFT_STORAGE = Storage(key: "turn_left", defaultValue: "L")
}

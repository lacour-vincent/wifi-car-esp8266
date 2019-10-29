import Foundation

struct Preferences {
    private let defaults = UserDefaults.standard
    
    private func getUserDefaultValue(storage: Storage) -> String {
        let key = storage.key
        let defaultValue = storage.defaultValue
        return defaults.string(forKey: key) ?? defaultValue
    }
    
    func getIpAddress() -> String { getUserDefaultValue(storage: Constants.IP_ADDRESS_ST0RAGE) }
    func getPort() -> String { getUserDefaultValue(storage: Constants.PORT_STORAGE) }
    
    func getMoveForwardValue() -> String { getUserDefaultValue(storage: Constants.MOVE_FORWARD_STORAGE) }
    func getMoveBackwardValue() -> String { getUserDefaultValue(storage: Constants.MOVE_BACKWARD_STORAGE) }
    func getStopValue() -> String { getUserDefaultValue(storage: Constants.MOVE_STOP_STORAGE) }
    func getTurnRightValue() -> String { getUserDefaultValue(storage: Constants.TURN_RIGHT_STORAGE) }
    func getTurnLeftValue() -> String { getUserDefaultValue(storage: Constants.TURN_LEFT_STORAGE) }
    
}

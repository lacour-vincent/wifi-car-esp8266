import Foundation

struct Preferences {
    private let defaults = UserDefaults.standard
    
    private func getUserDefaultValue(storage: Storage) -> String {
        let key = storage.key
        let defaultValue = storage.defaultValue
        return defaults.string(forKey: key) ?? defaultValue
    }
    
    func getIpAddress() -> String { getUserDefaultValue(storage: Keys.IP_ADDRESS_ST0RAGE) }
    func getPort() -> String { getUserDefaultValue(storage: Keys.PORT_STORAGE) }
    
    func getMoveForwardValue() -> String { getUserDefaultValue(storage: Keys.MOVE_FORWARD_STORAGE) }
    func getMoveBackwardValue() -> String { getUserDefaultValue(storage: Keys.MOVE_BACKWARD_STORAGE) }
    func getStopValue() -> String { getUserDefaultValue(storage: Keys.MOVE_STOP_STORAGE) }
    func getTurnRightValue() -> String { getUserDefaultValue(storage: Keys.TURN_RIGHT_STORAGE) }
    func getTurnLeftValue() -> String { getUserDefaultValue(storage: Keys.TURN_LEFT_STORAGE) }
}

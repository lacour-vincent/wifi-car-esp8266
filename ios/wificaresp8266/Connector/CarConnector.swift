import Alamofire

struct CarConnector {
    let preferences = Preferences()
    var apiUrl: String

    init() {
        apiUrl = "http://\(preferences.getIpAddress()):\(preferences.getPort())"
    }

    func moveForward() { sendMoveRequest(dir: preferences.getMoveForwardValue()) }
    func moveBackward() { sendMoveRequest(dir: preferences.getMoveBackwardValue()) }
    func stopMoving() { sendMoveRequest(dir: preferences.getStopValue()) }
    func turnRight() { sendMoveRequest(dir: preferences.getTurnRightValue()) }
    func turnLeft() { sendMoveRequest(dir: preferences.getTurnLeftValue()) }

    private func sendMoveRequest(dir: String) {
        let url = "\(apiUrl)/move?dir=\(dir)"
        AF.request(url, method: .get)
            .responseString { response in
                print(response)
            }
    }
}

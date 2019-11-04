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

    func actionOne() { sendActionRequest(type: preferences.getActionOneValue()) }
    func actionTwo() { sendActionRequest(type: preferences.getActionTwoValue()) }
    func actionThree() { sendActionRequest(type: preferences.getActionThreeValue()) }
    func actionFour() { sendActionRequest(type: preferences.getActionFourValue()) }
    func actionFive() { sendActionRequest(type: preferences.getActionFiveValue()) }
    func actionSix() { sendActionRequest(type: preferences.getActionSixValue()) }
    func actionSeven() { sendActionRequest(type: preferences.getActionSevenValue()) }
    func actionHeight() { sendActionRequest(type: preferences.getActionHeightValue()) }

    private func sendMoveRequest(dir: String) {
        let url = "\(apiUrl)/move?dir=\(dir)"
        AF.request(url, method: .get)
            .responseString { response in
                print(response)
            }
    }

    private func sendActionRequest(type: String) {
        let url = "\(apiUrl)/action?type=\(type)"
        AF.request(url, method: .get)
            .responseString { response in
                print(response)
            }
    }
}

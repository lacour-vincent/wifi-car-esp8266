//
//  CarConnector.swift
//  wificaresp8266
//
//  Created by VLA3795 on 29/10/2019.
//  Copyright © 2019 Vincent Lacour. All rights reserved.
//

import Alamofire
import Foundation

struct CarConnector {
    let apiUrl: String = "http://192.168.0.18:5000"

    func moveForward() { sendMoveRequest(dir: "F") }
    func moveBackward() { sendMoveRequest(dir: "B") }
    func stopMoving() { sendMoveRequest(dir: "S") }
    func turnRight() { sendMoveRequest(dir: "R") }
    func turnLeft() { sendMoveRequest(dir: "L") }

    private func sendMoveRequest(dir: String) {
        let url = "\(self.apiUrl)/move?dir=\(dir)"
        AF.request(url, method: .get)
            .responseString { response in
                print(response)
            }
    }
}

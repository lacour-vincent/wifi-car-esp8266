import UIKit

class WelcomeController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func onTouchAccelerometerMenu(_ sender: UIButton) {
        showUnavailableAlert()
    }
    
    @IBAction func onTouchVoiceMenu(_ sender: UIButton) {
        showUnavailableAlert()
    }
    
    @IBAction func onSettingsClick(_ sender: UIBarButtonItem) {
        guard let settingsUrl = URL(string: UIApplication.openSettingsURLString) else {
            return
        }
        if UIApplication.shared.canOpenURL(settingsUrl) {
            UIApplication.shared.open(settingsUrl, completionHandler: nil)
        }
    }
    
    @IBAction func onClickInformation(_ sender: UIBarButtonItem) {
        let title = "Information"
        let message = """
        The source code of my wifi car is available on my github:
        https://github.com/lacour-vincent/wifi-car-esp8266
        The default commands sent are:
        • http://IP:PORT/move?dir=F
        • http://IP:PORT/move?dir=B
        • http://IP:PORT/move?dir=S
        • http://IP:PORT/move?dir=R
        • http://IP:PORT/move?dir=L
        • http://IP:PORT/action?type=(1-8)
        """
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
        present(alert, animated: true)
    }
    
    private func showUnavailableAlert() {
        let alert = UIAlertController(title: "Not available", message: nil, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
        present(alert, animated: true)
    }
}

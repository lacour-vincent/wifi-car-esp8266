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
    
    private func showUnavailableAlert() {
        let alert = UIAlertController(title: "Not available", message: "", preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
        present(alert, animated: true)
    }
}

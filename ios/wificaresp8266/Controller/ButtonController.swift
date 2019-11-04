import UIKit

class ButtonController: UIViewController {
    let carConnector: CarConnector = CarConnector()
    
    @IBOutlet var arrowUpButton: UIButton!
    @IBOutlet var arrowRightButton: UIButton!
    @IBOutlet var arrowDownButton: UIButton!
    @IBOutlet var arrowLeftButton: UIButton!
    
    @IBOutlet var actionOneButton: UIButton!
    @IBOutlet var actionTwoButton: UIButton!
    @IBOutlet var actionThreeButton: UIButton!
    @IBOutlet var actionFourButton: UIButton!
    @IBOutlet var actionFiveButton: UIButton!
    @IBOutlet var actionSixButton: UIButton!
    @IBOutlet var actionSevenButton: UIButton!
    @IBOutlet var actionHeightButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func onTouchArrowDown(_ sender: UIButton) {
        handleOnTouchArrowDown(sender: sender)
    }
    
    @IBAction func onTouchArrowUpInside(_ sender: UIButton) {
        handleOnTouchArrowUp(sender: sender)
    }
    
    @IBAction func onTouchArrowUpOutside(_ sender: UIButton) {
        handleOnTouchArrowUp(sender: sender)
    }
    
    @IBAction func onTouchActionDown(_ sender: UIButton) {
        handleOnTouchActionDown(sender: sender)
    }
    
    func handleOnTouchArrowDown(sender: UIButton) {
        switch sender {
            case arrowUpButton:
                carConnector.moveForward()
            case arrowDownButton:
                carConnector.moveBackward()
            case arrowRightButton:
                carConnector.turnRight()
            case arrowLeftButton:
                carConnector.turnLeft()
            default:
                break
        }
    }
    
    func handleOnTouchArrowUp(sender: UIButton) {
        carConnector.stopMoving()
    }
    
    func handleOnTouchActionDown(sender: UIButton) {
        switch sender {
            case actionOneButton:
                carConnector.actionOne()
            case actionTwoButton:
                carConnector.actionTwo()
            case actionThreeButton:
                carConnector.actionThree()
            case actionFourButton:
                carConnector.actionFour()
            case actionFiveButton:
                carConnector.actionFive()
            case actionSixButton:
                carConnector.actionSix()
            case actionSevenButton:
                carConnector.actionSeven()
            case actionHeightButton:
                carConnector.actionHeight()
            default:
                break
        }
    }
    
    @IBAction func onClickInformation(_ sender: UIBarButtonItem) {
        showInformationAlert()
    }
    
    private func showInformationAlert() {
        let title = "Information"
        let message = """
        Keep pressing a move button to send the command.
        The "stop" command is sent when you release the button.
        The "action" command is sent when you click on the action button.
        """
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
        present(alert, animated: true)
    }
}

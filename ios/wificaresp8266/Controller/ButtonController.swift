import UIKit

class ButtonController: UIViewController {
    let carConnector: CarConnector = CarConnector()
    
    @IBOutlet var arrowUpButton: UIButton!
    @IBOutlet var arrowRightButton: UIButton!
    @IBOutlet var arrowDownButton: UIButton!
    @IBOutlet var arrowLeftButton: UIButton!
    
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
    
    func handleOnTouchArrowDown(sender: UIButton) {
        switch sender {
            case arrowUpButton:
                carConnector.moveForward()
                arrowUpButton.setImage(UIImage(named: "arrow_up_pressed.png"), for: .normal)
            case arrowDownButton:
                carConnector.moveBackward()
                arrowDownButton.setImage(UIImage(named: "arrow_down_pressed.png"), for: .normal)
            case arrowRightButton:
                carConnector.turnRight()
                arrowRightButton.setImage(UIImage(named: "arrow_right_pressed.png"), for: .normal)
            case arrowLeftButton:
                carConnector.turnLeft()
                arrowLeftButton.setImage(UIImage(named: "arrow_left_pressed.png"), for: .normal)
            default:
                break
        }
    }
    
    func handleOnTouchArrowUp(sender: UIButton) {
        carConnector.stopMoving()
        switch sender {
            case arrowUpButton:
                arrowUpButton.setImage(UIImage(named: "arrow_up.png"), for: .normal)
            case arrowDownButton:
                arrowDownButton.setImage(UIImage(named: "arrow_down.png"), for: .normal)
            case arrowRightButton:
                arrowRightButton.setImage(UIImage(named: "arrow_right.png"), for: .normal)
            case arrowLeftButton:
                arrowLeftButton.setImage(UIImage(named: "arrow_left.png"), for: .normal)
            default:
                break
        }
    }
}

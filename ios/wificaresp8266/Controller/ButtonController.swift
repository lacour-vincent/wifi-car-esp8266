import UIKit

class ButtonController: UIViewController {
    @IBOutlet var arrowUpButton: UIButton!
    @IBOutlet var arrowRightButton: UIButton!
    @IBOutlet var arrowDownButton: UIButton!
    @IBOutlet var arrowLeftButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        arrowUpButton.adjustsImageWhenHighlighted = false
        arrowRightButton.adjustsImageWhenHighlighted = false
        arrowDownButton.adjustsImageWhenHighlighted = false
        arrowLeftButton.adjustsImageWhenHighlighted = false
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
                arrowUpButton.setImage(UIImage(named: "arrow_up_pressed.png"), for: UIControl.State.normal)
            case arrowDownButton:
                arrowDownButton.setImage(UIImage(named: "arrow_down_pressed.png"), for: UIControl.State.normal)
            case arrowRightButton:
                arrowRightButton.setImage(UIImage(named: "arrow_right_pressed.png"), for: UIControl.State.normal)
            case arrowLeftButton:
                arrowLeftButton.setImage(UIImage(named: "arrow_left_pressed.png"), for: UIControl.State.normal)
            default:
                break
        }
    }
    
    func handleOnTouchArrowUp(sender: UIButton) {
        switch sender {
            case arrowUpButton:
                arrowUpButton.setImage(UIImage(named: "arrow_up.png"), for: UIControl.State.normal)
            case arrowDownButton:
                arrowDownButton.setImage(UIImage(named: "arrow_down.png"), for: UIControl.State.normal)
            case arrowRightButton:
                arrowRightButton.setImage(UIImage(named: "arrow_right.png"), for: UIControl.State.normal)
            case arrowLeftButton:
                arrowLeftButton.setImage(UIImage(named: "arrow_left.png"), for: UIControl.State.normal)
            default:
                break
        }
    }
}

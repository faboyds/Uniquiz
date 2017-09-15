//
//  QuizInfoViewController.swift
//  UniQuiziOS
//
//  Created by Pedro Neto on 09/09/17.
//  Copyright © 2017 Phoenix. All rights reserved.
//

import UIKit

class QuizInfoViewController: UIViewController {

    
    @IBOutlet var fiveStars: UIButton!
    @IBOutlet var fourStar: UIButton!
    @IBOutlet var threeStars: UIButton!
    @IBOutlet var twoStar: UIButton!
    @IBOutlet var oneStar: UIButton!
    @IBOutlet weak var wrongAnswers: UILabel!
    @IBOutlet weak var dificultyLabel: UILabel!
    @IBOutlet weak var quizNameLabel: UILabel!
    @IBOutlet weak var righttAnswers: UILabel!
    var stars = 0
    var quiz : SimplifiedQuiz?
    @IBOutlet var startingButton: UIButton!
    var solution : Solution?
    var fullQuiz : Quiz?
    var loadedFullQuiz = false
    func getFullQuiz(){
        guard let url = URL(string: Properties.getQuizURL(quizPk: String((quiz?.pk)!))) else {return}
        print(url)
        let session = URLSession.shared
        session.dataTask(with: url) { (data, response, error) in
            guard let response = response else {
                return
            }
            print(response)
            if let data = data {
                do{
                    self.fullQuiz = try  JSONDecoder().decode(Quiz.self, from: data)
                    self.loadedFullQuiz = true
                }
                catch{
                    print("Unable to decode")
                }
            }
            }.resume()
    }
    
    func getPrevSolution(){
        guard let url = URL(string: Properties.getSolutionsURL(quizPk: String((quiz?.pk)!),user : String((Properties.user?.email)!))) else {return}
        print(url)
        let session = URLSession.shared
        session.dataTask(with: url) { (data, response, error) in
            guard let response = response else {
                return
            }
            print(response)
            if let data = data {
                do{
                    self.solution = try  JSONDecoder().decode(Solution.self, from: data)
                    DispatchQueue.main.sync {
                        if self.solution != nil{
                        self.righttAnswers.text = String((self.solution?.rightAnswers)!)
                        self.wrongAnswers.text = String((self.solution?.wrongAnswers)!)
                        }
                    }
                }
                catch{
                    print("Unable to decode")
                }
               
            }
            }.resume()
    }
    override func viewWillAppear(_ animated: Bool) {
        getPrevSolution()
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        startingButton.layer.cornerRadius = 15
        startingButton.layer.borderWidth = 1.0
        startingButton.layer.borderColor = UIColor(white: 1.0, alpha: 0.7).cgColor
        wrongAnswers.text = "0"
        righttAnswers.text = "0"
        getPrevSolution()
        dificultyLabel.text = (quiz?.difficulty)!
        quizNameLabel.text = (quiz?.title)!
        getFullQuiz()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        if identifier == "startQuizSegue"{
            if fullQuiz != nil {return true}
            else {return false}
        }
        return true
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "startQuizSegue" {
           guard let destination = segue.destination as? QuizViewController else { fatalError()}
            destination.quiz = fullQuiz
        }
    }

    @IBAction func onOneStarSelected(_ sender: UIButton) {
        if stars == 0 { oneStar.setImage(UIImage(named : "icon"), for: UIControlState.normal) }
        else if stars > 1 {
            twoStar.setImage(UIImage(named : "icon3"), for: UIControlState.normal)
            if stars > 2{
                threeStars.setImage(UIImage(named : "icon3"), for: UIControlState.normal)
                if stars > 3 {
                    fourStar.setImage(UIImage(named : "icon3"), for: UIControlState.normal)
                    if stars > 4{
                        fiveStars.setImage(UIImage(named : "icon3"), for: UIControlState.normal)
                    }
                    
                }
            }
            
        }
        stars = 1
    }
    
    
    @IBAction func onTwoStarSelected(_ sender: UIButton) {
        if stars  < 2  {
            oneStar.setImage(UIImage(named : "icon"), for: UIControlState.normal)
            twoStar.setImage(UIImage(named : "icon"), for: UIControlState.normal)
        }
            if stars > 2{
                threeStars.setImage(UIImage(named : "icon3"), for: UIControlState.normal)
                if stars > 3 {
                    fourStar.setImage(UIImage(named : "icon3"), for: UIControlState.normal)
                    if stars > 4{
                        fiveStars.setImage(UIImage(named : "icon3"), for: UIControlState.normal)
                    
                    
                }
            }
            
        }
        stars = 2
    }
    
    
    
    
    @IBAction func onThreeStarSelected(_ sender: UIButton) {
        if stars < 3 {
            oneStar.setImage(UIImage(named : "icon"), for: UIControlState.normal)
            twoStar.setImage(UIImage(named : "icon"), for: UIControlState.normal)
            threeStars.setImage(UIImage(named : "icon"), for: UIControlState.normal)
        }
            if stars > 3 {
                fourStar.setImage(UIImage(named : "icon3"), for: UIControlState.normal)
                if stars > 4{
                    fiveStars.setImage(UIImage(named : "icon3"), for: UIControlState.normal)
                    
                    
                }
            
            
        }
        stars = 3
    }
    
    @IBAction func onFourStarSelected(_ sender: UIButton) {
        if stars < 4 {
            oneStar.setImage(UIImage(named : "icon"), for: UIControlState.normal)
            twoStar.setImage(UIImage(named : "icon"), for: UIControlState.normal)
            threeStars.setImage(UIImage(named : "icon"), for: UIControlState.normal)
            fourStar.setImage(UIImage(named : "icon"), for: UIControlState.normal)

        }
            if stars > 4{
                fiveStars.setImage(UIImage(named : "icon3"), for: UIControlState.normal)
            }
        stars = 4
        
    }
    
    @IBAction func onFiveStarSelected(_ sender: UIButton) {
        oneStar.setImage(UIImage(named : "icon"), for: UIControlState.normal)
        twoStar.setImage(UIImage(named : "icon"), for: UIControlState.normal)
        threeStars.setImage(UIImage(named : "icon"), for: UIControlState.normal)
        fourStar.setImage(UIImage(named : "icon"), for: UIControlState.normal)
        fiveStars.setImage(UIImage(named : "icon"), for: UIControlState.normal)
        stars = 5
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}

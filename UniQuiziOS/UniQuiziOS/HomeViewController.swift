//
//  HomeViewController.swift
//  UniQuiziOS
//
//  Created by Pedro Neto on 08/09/17.
//  Copyright © 2017 Phoenix. All rights reserved.
//

import UIKit


class HomeViewController: UIViewController {

    
    override func viewWillAppear(_ animated: Bool) {
        getUser()
        
    }
    
    func getUser(){
        guard let url = URL(string: Properties.getUser(user: (Properties.user?.email)!)) else {return}
        print(url)
        let session = URLSession.shared
        session.dataTask(with: url) { (data, response, error) in
        guard let response = response else {
        return
        }
        print(response)
        if let data = data {
        do{
        let user = try  JSONDecoder().decode(User.self, from: data)
            Properties.user = user
        DispatchQueue.main.sync {
            self.updateFields()
            }
        }
        
        catch{
        print("Unable to decode")
        }
            }
        
        
        }.resume()
    }

func updateFields(){
    totalQuizzesPassed.text = String((Properties.user?.userStatistics.totalQuizzesPassed)!)
    totalQuizzesSolved.text = String((Properties.user?.userStatistics.totalQuizzesSolved)!)
    wrongAnswers.text = String((Properties.user?.userStatistics.totalWrongAnswers)!)
    rightAnswers.text = String((Properties.user?.userStatistics.totalRightAnswers)!)
    
}

    @IBOutlet weak var totalQuizzesPassed: UILabel!
    @IBOutlet weak var totalQuizzesSolved: UILabel!
    @IBOutlet weak var wrongAnswers: UILabel!
    @IBOutlet weak var rightAnswers: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        updateFields()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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

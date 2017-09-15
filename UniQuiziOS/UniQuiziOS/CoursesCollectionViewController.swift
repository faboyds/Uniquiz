//
//  CoursesCollectionViewController.swift
//  UniQuiziOS
//
//  Created by Pedro Neto on 10/09/17.
//  Copyright © 2017 Phoenix. All rights reserved.
//

import UIKit

private let reuseIdentifier = "coursesCollection"

class CoursesCollectionViewController: UICollectionViewController {

    
    var subjects = [Subject]()
    //fileprivate let sectionInsets = UIEdgeInsets(top: 50.0, left: 20.0, bottom: 50.0, right: 20.0)
    
    
    
    func getCourses(){
        guard let url = URL(string: Properties.getSubjectURL()) else {return}
        print(url)
        let session = URLSession.shared
        session.dataTask(with: url) { (data, response, error) in
            guard let response = response else {
                return
            }
            print(response)
            if let data = data {
                self.subjects.removeAll()
                do{
                    let newItems = try  JSONDecoder().decode([Subject].self, from: data)
                    self.subjects += newItems
                }
                catch{
                    print("Unable to decode")
                }
                DispatchQueue.main.sync {
                    self.collectionView?.reloadData()
                }
            }
            }.resume()
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        getCourses()
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Register cell classes

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using [segue destinationViewController].
        // Pass the selected object to the new view controller.
    }
    */

    // MARK: UICollectionViewDataSource

    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }


    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
       
        return subjects.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as? CoursesCollectionViewCell else{fatalError() }
    
        cell.courseText.text = subjects[indexPath.row].subjectName
       cell.courseImage.image = UIImage(named : "SubjectImage") //GetImage
        cell.courseText.textColor = UIColor.white
        cell.backgroundColor = UIColor(hexString: "#FFA000")
        cell.layer.cornerRadius = 10
        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let destination = segue.destination as! QuizzesFromSubjectTableViewController
        guard let selectedCell = sender as? CoursesCollectionViewCell else {
            return
        }
        guard let indexPath = collectionView?.indexPath(for: selectedCell) else {
            return
        }
        destination.subjectPk =  subjects[indexPath.row].pk
        destination.hidesBottomBarWhenPushed = true;
    }

    // MARK: UICollectionViewDelegate

    /*
    // Uncomment this method to specify if the specified item should be highlighted during tracking
    override func collectionView(_ collectionView: UICollectionView, shouldHighlightItemAt indexPath: IndexPath) -> Bool {
        return true
    }
    */

    /*
    // Uncomment this method to specify if the specified item should be selected
    override func collectionView(_ collectionView: UICollectionView, shouldSelectItemAt indexPath: IndexPath) -> Bool {
        return true
    }
    */

    /*
    // Uncomment these methods to specify if an action menu should be displayed for the specified item, and react to actions performed on the item
    override func collectionView(_ collectionView: UICollectionView, shouldShowMenuForItemAt indexPath: IndexPath) -> Bool {
        return false
    }

    override func collectionView(_ collectionView: UICollectionView, canPerformAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) -> Bool {
        return false
    }

    override func collectionView(_ collectionView: UICollectionView, performAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) {
    
    }
    */

}

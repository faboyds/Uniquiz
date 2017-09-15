//
//  FieldCollectionViewController.swift
//  UniQuiziOS
//
//  Created by Pedro Neto on 09/09/17.
//  Copyright © 2017 Phoenix. All rights reserved.
//

import UIKit

class FieldCollectionViewController: UICollectionViewController {

    
    var field = [Field]()
    //fileprivate let sectionInsets = UIEdgeInsets(top: 50.0, left: 20.0, bottom: 50.0, right: 20.0)

    
    
    func getFields(){
        guard let url = URL(string: Properties.getFieldsURL()) else {return}
        print(url)
        let session = URLSession.shared
        session.dataTask(with: url) { (data, response, error) in
            guard let response = response else {
                return
            }
            print(response)
            if let data = data {
                self.field.removeAll()
                do{
                    let newItems = try  JSONDecoder().decode([Field].self, from: data)
                    self.field += newItems
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
        
        
        getFields()
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Register cell classes
      // self.collectionView!.register(FieldCollectionViewCell.self, forCellWithReuseIdentifier: "fieldsCollection")

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
        // #warning Incomplete implementation, return the number of items
      
        return field.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
         let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "fieldsCollection", for: indexPath) as! FieldCollectionViewCell 
        cell.fieldText.text = field[indexPath.row].fieldName
        cell.fieldText.textColor = UIColor.white
        cell.fieldImage.image = UIImage(named: "SubjectImage") //GetFieldImages
        cell.backgroundColor = UIColor(hexString: "#FFA000")
        cell.layer.cornerRadius = 10
        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let destination = segue.destination as! CoursesTableViewController
        guard let selectedCell = sender as? FieldCollectionViewCell else {
            return
        }
        guard let indexPath = collectionView?.indexPath(for: selectedCell) else {
            return
        }
        destination.fieldPk =  field[indexPath.row].pk
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

//
//  SearchTableViewController.swift
//  UniQuiziOS
//
//  Created by Pedro Neto on 08/09/17.
//  Copyright © 2017 Phoenix. All rights reserved.
//

import UIKit

class SearchTableViewController: UITableViewController , UISearchBarDelegate{

    var quizArray = [SimplifiedQuiz]()
    
    @IBOutlet weak var searchBar: UISearchBar!
    func doSearch(){
        self.view.endEditing(true)
        guard let text = searchBar.text else {return}
        guard let url = URL(string: Properties.getSearchURL(toSearch: String(text))) else {return}
        print(url)
        let session = URLSession.shared
        session.dataTask(with: url) { (data, response, error) in
            guard let response = response else {
                return
            }
            print(response)
            if let data = data {
                self.quizArray.removeAll()
                do{
                    let newItems = try  JSONDecoder().decode([SimplifiedQuiz].self, from: data)
                    self.quizArray += newItems
                }
                catch{
                    print("Unable to decode")
                }
                DispatchQueue.main.sync {
                    self.refreshControl?.beginRefreshing()
                    self.tableView.reloadData()
                    self.refreshControl?.endRefreshing()
                }
            }
            }.resume()
    }
    
    func searchBarResultsListButtonClicked(_ searchBar: UISearchBar){
        doSearch()
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar){
        doSearch()
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        searchBar.delegate = self
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return quizArray.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "quizzesCell", for: indexPath) as? SearchTableViewCell else {fatalError()}
        
        cell.quizName.text = quizArray[indexPath.row].title
        cell.quizTheme.text = quizArray[indexPath.row].difficulty
        

        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "quizInfoSegue" {
            let destination = segue.destination as? QuizInfoViewController
            guard let selectedCell = sender as? SearchTableViewCell else {
                return
            }
            guard let indexPath = tableView.indexPath(for: selectedCell) else {
                return
            }
            destination?.quiz = self.quizArray[indexPath.row]
           destination?.hidesBottomBarWhenPushed = true;

        }
    }
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    } 

    
    func textFieldShouldReturn(textField: UITextField) -> Bool{
        textField.resignFirstResponder()
        return true
    }

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}

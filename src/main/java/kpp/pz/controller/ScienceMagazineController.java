package kpp.pz.controller;

import kpp.pz.dao.DAOFactory;
import kpp.pz.dao.IMyDAO;
import kpp.pz.dao.TypeDAO;
import kpp.pz.hierarchy.PrintedMaterial;
import kpp.pz.hierarchy.ScienceMagazine;
import kpp.pz.myList.MyListImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
@RequestMapping("/scienceMagazinePages")
public class ScienceMagazineController {
    private final IMyDAO<PrintedMaterial> dao;

    public ScienceMagazineController() throws SQLException {
        this.dao = DAOFactory.getDAOInstance(TypeDAO.MySQL);
//        this.dao = DAOFactory.getDAOInstance(TypeDAO.MyList);
    }

    @GetMapping
    public String showAllScienceMagazines(Model model) throws SQLException {
        MyListImpl<PrintedMaterial> list = dao.getAllScienceMagazines();
        model.addAttribute("allScienceMagazines", list);
        return "scienceMagazinePages/scienceMagazinesPage";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String showAddScienceMagazineView(Model model) {
        ScienceMagazine scienceMagazine = new ScienceMagazine();
        model.addAttribute("scienceMagazine", scienceMagazine);
        return "scienceMagazinePages/addScienceMagazinePage";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addScienceMagazine(Model model, ScienceMagazine scienceMagazine) throws SQLException {
        dao.add(scienceMagazine);
        return "redirect:/scienceMagazinePages";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String showEditScienceMagazineView(Model model) {
        return "scienceMagazinePages/findScienceMagazineToEditPage";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    public String editScienceMagazine(@RequestParam("title") String title, Model model) throws SQLException {
        PrintedMaterial scienceMagazine = dao.getScienceMagazineByTitle(title);
        if (scienceMagazine == null) {
            model.addAttribute("errorMessage", "Science Magazine not found");
            return "redirect:/scienceMagazinePages";
        }
        model.addAttribute("scienceMagazine", scienceMagazine);
        return "scienceMagazinePages/editScienceMagazinePage";
    }

    @RequestMapping(value = {"/edit/save"}, method = RequestMethod.POST)
    public String saveEditedScienceMagazine(ScienceMagazine scienceMagazine) throws SQLException {
        dao.update(scienceMagazine);
        return "redirect:/scienceMagazinePages";
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.GET)
    public String showDeleteScienceMagazineView(Model model) {
        return "scienceMagazinePages/deleteScienceMagazinePage";
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.POST)
    public String deleteBook(@RequestParam("title") String title, Model model) throws SQLException {
        PrintedMaterial scienceMagazine = dao.getScienceMagazineByTitle(title);
        if (scienceMagazine == null) {
            model.addAttribute("errorMessage", "Science Magazine not found");
            return "scienceMagazinePages/deleteScienceMagazinePage";
        }
        dao.deleteByTitle(title);
        return "redirect:/scienceMagazinePages";
    }

    @RequestMapping(value = {"/byScientificField"}, method = RequestMethod.GET)
    public String showGetScienceMagazinesByScientificFieldView(Model model) {
        return "scienceMagazinePages/findScienceMagazinesByScientificField";
    }

    @RequestMapping(value = {"/byScientificField"}, method = RequestMethod.POST)
    public String getScienceMagazinesScientificField(@RequestParam("scientificField") String scientificField, Model model) throws SQLException {
        MyListImpl<PrintedMaterial> list = dao.getScienceMagazinesByScientificField(scientificField);
        model.addAttribute("allScienceMagazines", list);
        return "scienceMagazinePages/findScienceMagazinesByScientificField";
    }
}

package fr.hoenheimsports.dto.game.importFromCsv;

import fr.hoenheimsports.gamedomain.exception.FileDataException;

import java.util.List;

public class ImportCSVGameUnplayedDTO {
    private String numPoule;
    private String competition;
    private String poule;
    private String J;
    private String le;
    private String horaire;
    private String clubRec;
    private String clubVis;

    private String arb1Designe;
    private String arb2Designe;

    private String codeRenc;
    private String nomSalle;
    private String adresseSalle;
    private String CP;
    private String Ville;
    private String colle;
    private String CoulRec;
    private String CoulGardRec;
    private String CoulVis;
    private String CoulGardVis;
    private String EntRec;
    private String TelEntRec;
    private String NumRec;
    private String NumVis;
    private String scRec;
    private String scVis;
    private String fdme;
    private String arb1Sifle;
    private String arb2Sifle;
    private String EntVis;
    private String TelEntVis;
    private String semaine;
/*
        variable doesn't use :

        private String CorrespRec;
        private String TelCorrespRec;
        private String CorrespVis;
        private String TelCorrespVis;
        private String fdmeRec;
        private String fdmeVis;
        private String penRec;
        private String penVis;
        private String forfRec;
        private String forfVis;
        private String secretaire;
        private String chronometreur;
        private String respSalle;
        private String tuteurTable;
        private String Etat;
        private String DateArrivee;
        private String observateur;
        private String delegue;
        private String clubHote;

       */

    public ImportCSVGameUnplayedDTO(List<String> headers, List<String> csvLine) throws FileDataException {
        if (headers.size() != csvLine.size()) {
            throw new FileDataException("csv file data hasn't the required format");
        }
        int colonne = 0;
        for (String header : headers) {
            this.assignHeaderNameToCellValue(header, csvLine.get(colonne));
            colonne++;
        }
    }


    private void assignHeaderNameToCellValue(String headerName, String cellValue) throws FileDataException {
        cellValue = cellValue.trim().isEmpty() ? null : cellValue;
        switch (headerName) {

            case "num poule" -> {
                if (cellValue == null) {
                    throw new FileDataException("'num poule' column is empty and should be required");
                }
                this.numPoule = cellValue;
            }
            case "competition" -> {
                if (cellValue == null) {
                    throw new FileDataException("'competition' column is empty and should be required");
                }
                this.competition = cellValue;
            }
            case "poule" -> {
                if (cellValue == null) {
                    throw new FileDataException("'poule' column is empty and should be required");
                }
                this.poule = cellValue;
            }
            case "J" -> {
                if (cellValue == null) {
                    throw new FileDataException("'J' column is empty and should be required");
                }
                this.J = cellValue;
            }
            case "le" -> this.le = cellValue;
            case "horaire" -> this.horaire = cellValue;
            case "club rec" -> {
                if (cellValue == null) {
                    throw new FileDataException("'club rec' column is empty and should be required");
                }
                this.clubRec = cellValue;
            }
            case "club vis" -> {
                if (cellValue == null) {
                    throw new FileDataException("'club vis' column is empty and should be required");
                }
                this.clubVis = cellValue;
            }

            case "arb1 designe" -> this.arb1Designe = cellValue;
            case "arb2 designe" -> this.arb2Designe = cellValue;

            case "code renc" -> {
                if (cellValue == null) {
                    throw new FileDataException("'code renc' column is empty and should be required");
                }
                this.codeRenc = cellValue;
            }
            case "nom salle" -> this.nomSalle = cellValue;
            case "adresse salle" -> this.adresseSalle = cellValue;
            case "CP" -> this.CP = cellValue;
            case "Ville" -> this.Ville = cellValue;
            case "colle" -> this.colle = cellValue;
            case "Coul. Rec" -> this.CoulRec = cellValue;
            case "Coul. Gard. Rec" -> this.CoulGardRec = cellValue;
            case "Coul. Vis" -> this.CoulVis = cellValue;
            case "Coul. Gard. Vis" -> this.CoulGardVis = cellValue;
            case "Ent. Rec" -> this.EntRec = cellValue;
            case "Tel Ent. Rec" -> this.TelEntRec = cellValue;
            case "Ent. Vis" -> this.EntVis = cellValue;
            case "Tel Ent. Vis" -> this.TelEntVis = cellValue;
            case "Num rec" -> {
                if (cellValue == null) {
                    throw new FileDataException("'Num rec' column is empty and should be required");
                }
                this.NumRec = cellValue;
            }
            case "Num vis" -> {
                if (cellValue == null) {
                    throw new FileDataException("'Num vis' column is empty and should be required");
                }
                this.NumVis = cellValue;
            }
            case "FDME" -> this.fdme = cellValue;
            case "sc rec" -> this.scRec = cellValue;
            case "sc vis" -> this.scVis = cellValue;

            case "arb1 sifle" -> this.arb1Sifle = cellValue;
            case "arb2 sifle" -> this.arb2Sifle = cellValue;
            case "semaine" -> {
                if(cellValue == null) {
                    throw new FileDataException("'Semaine' column is empty and should be required");
                }
                this.semaine = cellValue;
            }
/*

                case "fdme rec" -> this.fdmeRec = cellValue;
                case "club hote" -> this.clubHote = cellValue;
                case "observateur" -> this.observateur = cellValue;
                case "delegue" -> this.delegue = cellValue;
                case "Corresp. Rec" -> this.CorrespRec = cellValue;
                case "Tel Corresp. Rec" -> this.TelCorrespRec = cellValue;
                case "fdme vis" -> this.fdmeVis = cellValue;
                case "pen. rec" -> this.penRec = cellValue;
                case "pen. vis" -> this.penVis = cellValue;
                case "forf. rec" -> this.forfRec = cellValue;
                case "forf. vis" -> this.forfVis = cellValue;
                case "Corresp. Vis" -> this.CorrespVis = cellValue;
                case "Tel Corresp. Vis" -> this.TelCorrespVis = cellValue;
                case "secretaire" -> this.secretaire = cellValue;
                case "chronometreur" -> this.chronometreur = cellValue;
                case "resp salle" -> this.respSalle = cellValue;
                case "tuteur table" -> this.tuteurTable = cellValue;
                case "Etat" -> this.Etat = cellValue;
                case "Date Arrivee" -> this.DateArrivee = cellValue;*/
        }
    }


    public String getNumPoule() {
        return numPoule;
    }

    public String getCompetition() {
        return competition;
    }

    public String getPoule() {
        return poule;
    }

    public String getJ() {
        return J;
    }

    public String getLe() {
        return le;
    }

    public String getHoraire() {
        return horaire;
    }

    public String getClubRec() {
        return clubRec;
    }

    public String getClubVis() {
        return clubVis;
    }


    public String getArb1Designe() {
        return arb1Designe;
    }

    public String getArb2Designe() {
        return arb2Designe;
    }

    public String getCodeRenc() {
        return codeRenc;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public String getAdresseSalle() {
        return adresseSalle;
    }

    public String getCP() {
        return CP;
    }

    public String getVille() {
        return Ville;
    }

    public String getColle() {
        return colle;
    }

    public String getCoulRec() {
        return CoulRec;
    }

    public String getCoulGardRec() {
        return CoulGardRec;
    }

    public String getCoulVis() {
        return CoulVis;
    }

    public String getCoulGardVis() {
        return CoulGardVis;
    }

    public String getEntRec() {
        return EntRec;
    }

    public String getTelEntRec() {
        return TelEntRec;
    }


    public String getEntVis() {
        return EntVis;
    }

    public String getTelEntVis() {
        return TelEntVis;
    }


    public String getNumRec() {
        return NumRec;
    }

    public String getNumVis() {
        return NumVis;
    }

    public String getScRec() {
        return scRec;
    }

    public String getScVis() {
        return scVis;
    }

    public String getFDME() {
        return fdme;
    }


    public String getArb1Sifle() {
        return arb1Sifle;
    }

    public String getArb2Sifle() {
        return arb2Sifle;
    }

    public String getSemaine() {
        return semaine;
    }
}



package fr.hoenheimsports.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.model.GlueAuthorization;
import fr.hoenheimsports.gamedomain.spi.ImportFileGame;
import fr.hoenheimsports.gamedomain.spi.exception.FileDataException;
import fr.hoenheimsports.gamedomain.spi.exception.FileException;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ImportCSVGame implements ImportFileGame {
    private static final List<String> headerNoPlayed = Arrays.asList(
            "semaine",
            "num poule",
            "competition",
            "poule",
            "J",
            "le",
            "horaire",
            "club rec",
            "club vis",
            "club hote",
            "arb1 designe",
            "arb2 designe",
            "observateur",
            "delegue",
            "code renc",
            "nom salle",
            "adresse salle",
            "CP",
            "Ville",
            "colle",
            "Coul. Rec",
            "Coul. Gard. Rec",
            "Coul. Vis",
            "Coul. Gard. Vis",
            "Ent. Rec",
            "Tel Ent. Rec",
            "Corresp. Rec",
            "Tel Corresp. Rec",
            "Ent. Vis",
            "Tel Ent. Vis",
            "Corresp. Vis",
            "Tel Corresp. Vis",
            "Num rec",
            "Num vis"
    );

    private static final List<String> headerPlayed = Arrays.asList(
            "semaine",
            "num poule",
            "competition",
            "poule",
            "J",
            "le",
            "horaire",
            "club rec",
            "club vis",
            "sc rec",
            "sc vis",
            "fdme rec",
            "fdme vis",
            "pen. rec",
            "pen. vis",
            "forf. rec",
            "forf. vis",
            "arb1 designe",
            "arb2 designe",
            "arb1 sifle",
            "arb2 sifle",
            "secretaire",
            "chronometreur",
            "observateur",
            "delegue",
            "resp salle",
            "tuteur table",
            "code renc",
            "Num rec",
            "Num vis",
            "Etat",
            "Forfait",
            "Penalite",
            "FDME",
            "Date Arrivee"
    );

    @Override
    public List<Game> importFileGame(InputStream fileStream) throws FileDataException, FileException {

        List<List<String>> csvData = this.parseToList(fileStream);
        if (csvData.isEmpty()) {
            throw new FileDataException();
        }
        List<String> csvDataHeader = csvData.remove(0);
        List<CSVLine> csvDataWithHeader = new ArrayList<>();
        for (List<String> csvDataLine : csvData) {
            csvDataWithHeader.add(new CSVLine(headerPlayed, csvDataLine));
        }
        return null;

    }

    private List<List<String>> parseToList(InputStream fileStream) throws FileDataException, FileException {

        try (InputStreamReader reader = new InputStreamReader(new BOMInputStream(fileStream))) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
                return new ArrayList<>(csvReader.readAll().stream().map(line -> Arrays.asList(line)).toList());
            }
        } catch (IOException ioe) {
            throw new FileException();
        } catch (com.opencsv.exceptions.CsvException e) {
            throw new FileDataException();
        }

    }

    public Game mapCSVLineToGame(CSVLine csvLine){
    Game game = GameBuilder.builder()
                .withCode("test")
                .withDateTime(LocalDateTime.now())
                .withDay(dayBuilder -> dayBuilder.withNumber(1).build())
                .withCompetition(competitionBuilder -> competitionBuilder
                        .withName("Competition 1")
                        .withPool(poolBuilder -> poolBuilder
                                .withName("Pool1")
                                .withCode("code Pool 1")))
                .withHalle(halleBuilder -> halleBuilder
                        .withId(UUID.randomUUID())
                        .withName("Halle 1")
                        .withAddress(addressBuilder -> addressBuilder
                                .withStreet("rue 1")
                                .withPostalCode(67000)
                                .withCity("ville 1"))
                        .withGlueAuthorization(GlueAuthorization.AUTHORIZED))
                .withHomeTeam(teamBuilder -> teamBuilder
                        .withId(UUID.randomUUID())
                        .withClub(clubBuilder -> clubBuilder.)
                        .withCategory()
                        .withGender()
                        .withNumber()
                        .withTeamsColor()
                        .withCoach())
                .withVisitingTeam()
                .withScore()
                .withReferees()
                .withFDME()
                .build():
        return null;
    }

    private class CSVLine {

        private String semaine;
        private String numPoule;
        private String competition;
        private String poule;
        private String J;
        private String le;
        private String horaire;
        private String clubRec;
        private String clubVis;
        private String clubHote;
        private String arb1Designe;
        private String arb2Designe;
        private String observateur;
        private String delegue;
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
        private String CorrespRec;
        private String TelCorrespRec;
        private String EntVis;
        private String TelEntVis;
        private String CorrespVis;
        private String TelCorrespVis;
        private String NumRec;
        private String NumVis;
        private String scRec;
        private String scVis;
        private String fdmeRec;
        private String fdmeVis;
        private String penRec;
        private String penVis;
        private String forfRec;
        private String forfVis;
        private String arb1Sifle;
        private String arb2Sifle;
        private String secretaire;
        private String chronometreur;
        private String respSalle;
        private String tuteurTable;
        private String Etat;
        private String DateArrivee;

        public CSVLine(List<String> headers, List<String> csvLine) throws FileDataException {
            if (headers.size() != csvLine.size()) {
                throw new FileDataException();
            }
            int colonne = 0;
            for (String header : headers) {
                this.assignValueToVariable(header, csvLine.get(colonne));
                colonne++;
            }
        }


        private void assignValueToVariable(String var, String value) throws FileDataException {
            switch (var) {
                case "semaine" -> this.semaine = value;
                case "num poule" -> this.numPoule = value;
                case "competition" -> this.competition = value;
                case "poule" -> this.poule = value;
                case "J" -> this.J = value;
                case "le" -> this.le = value;
                case "horaire" -> this.horaire = value;
                case "club rec" -> this.clubRec = value;
                case "club vis" -> this.clubVis = value;
                case "club hote" -> this.clubHote = value;
                case "arb1 designe" -> this.arb1Designe = value;
                case "arb2 designe" -> this.arb2Designe = value;
                case "observateur" -> this.observateur = value;
                case "delegue" -> this.delegue = value;
                case "code renc" -> this.codeRenc = value;
                case "nom salle" -> this.nomSalle = value;
                case "adresse salle" -> this.adresseSalle = value;
                case "CP" -> this.CP = value;
                case "Ville" -> this.Ville = value;
                case "colle" -> this.colle = value;
                case "Coul. Rec" -> this.CoulRec = value;
                case "Coul. Gard. Rec" -> this.CoulGardRec = value;
                case "Coul. Vis" -> this.CoulVis = value;
                case "Coul. Gard. Vis" -> this.CoulGardVis = value;
                case "Ent. Rec" -> this.EntRec = value;
                case "Tel Ent. Rec" -> this.TelEntRec = value;
                case "Corresp. Rec" -> this.CorrespRec = value;
                case "Tel Corresp. Rec" -> this.TelCorrespRec = value;
                case "Ent. Vis" -> this.EntVis = value;
                case "Tel Ent. Vis" -> this.TelEntVis = value;
                case "Corresp. Vis" -> this.CorrespVis = value;
                case "Tel Corresp. Vis" -> this.TelCorrespVis = value;
                case "Num rec" -> this.NumRec = value;
                case "Num vis" -> this.NumVis = value;
                case "sc rec" -> this.scRec = value;
                case "sc vis" -> this.scVis = value;
                case "fdme rec" -> this.fdmeRec = value;
                case "fdme vis" -> this.fdmeVis = value;
                case "pen. rec" -> this.penRec = value;
                case "pen. vis" -> this.penVis = value;
                case "forf. rec" -> this.forfRec = value;
                case "forf. vis" -> this.forfVis = value;
                case "arb1 sifle" -> this.arb1Sifle = value;
                case "arb2 sifle" -> this.arb2Sifle = value;
                case "secretaire" -> this.secretaire = value;
                case "chronometreur" -> this.chronometreur = value;
                case "resp salle" -> this.respSalle = value;
                case "tuteur table" -> this.tuteurTable = value;
                case "Etat" -> this.Etat = value;
                case "Date Arrivee" -> this.DateArrivee = value;
                default -> {
                    throw new FileDataException();
                }
            }
        }

        public String getSemaine() {
            return semaine;
        }

        public void setSemaine(String semaine) {
            this.semaine = semaine;
        }

        public String getNumPoule() {
            return numPoule;
        }

        public void setNumPoule(String numPoule) {
            this.numPoule = numPoule;
        }

        public String getCompetition() {
            return competition;
        }

        public void setCompetition(String competition) {
            this.competition = competition;
        }

        public String getPoule() {
            return poule;
        }

        public void setPoule(String poule) {
            this.poule = poule;
        }

        public String getJ() {
            return J;
        }

        public void setJ(String j) {
            J = j;
        }

        public String getLe() {
            return le;
        }

        public void setLe(String le) {
            this.le = le;
        }

        public String getHoraire() {
            return horaire;
        }

        public void setHoraire(String horaire) {
            this.horaire = horaire;
        }

        public String getClubRec() {
            return clubRec;
        }

        public void setClubRec(String clubRec) {
            this.clubRec = clubRec;
        }

        public String getClubVis() {
            return clubVis;
        }

        public void setClubVis(String clubVis) {
            this.clubVis = clubVis;
        }

        public String getClubHote() {
            return clubHote;
        }

        public void setClubHote(String clubHote) {
            this.clubHote = clubHote;
        }

        public String getArb1Designe() {
            return arb1Designe;
        }

        public void setArb1Designe(String arb1Designe) {
            this.arb1Designe = arb1Designe;
        }

        public String getArb2Designe() {
            return arb2Designe;
        }

        public void setArb2Designe(String arb2Designe) {
            this.arb2Designe = arb2Designe;
        }

        public String getObservateur() {
            return observateur;
        }

        public void setObservateur(String observateur) {
            this.observateur = observateur;
        }

        public String getDelegue() {
            return delegue;
        }

        public void setDelegue(String delegue) {
            this.delegue = delegue;
        }

        public String getCodeRenc() {
            return codeRenc;
        }

        public void setCodeRenc(String codeRenc) {
            this.codeRenc = codeRenc;
        }

        public String getNomSalle() {
            return nomSalle;
        }

        public void setNomSalle(String nomSalle) {
            this.nomSalle = nomSalle;
        }

        public String getAdresseSalle() {
            return adresseSalle;
        }

        public void setAdresseSalle(String adresseSalle) {
            this.adresseSalle = adresseSalle;
        }

        public String getCP() {
            return CP;
        }

        public void setCP(String CP) {
            this.CP = CP;
        }

        public String getVille() {
            return Ville;
        }

        public void setVille(String ville) {
            Ville = ville;
        }

        public String getColle() {
            return colle;
        }

        public void setColle(String colle) {
            this.colle = colle;
        }

        public String getCoulRec() {
            return CoulRec;
        }

        public void setCoulRec(String coulRec) {
            CoulRec = coulRec;
        }

        public String getCoulGardRec() {
            return CoulGardRec;
        }

        public void setCoulGardRec(String coulGardRec) {
            CoulGardRec = coulGardRec;
        }

        public String getCoulVis() {
            return CoulVis;
        }

        public void setCoulVis(String coulVis) {
            CoulVis = coulVis;
        }

        public String getCoulGardVis() {
            return CoulGardVis;
        }

        public void setCoulGardVis(String coulGardVis) {
            CoulGardVis = coulGardVis;
        }

        public String getEntRec() {
            return EntRec;
        }

        public void setEntRec(String entRec) {
            EntRec = entRec;
        }

        public String getTelEntRec() {
            return TelEntRec;
        }

        public void setTelEntRec(String telEntRec) {
            TelEntRec = telEntRec;
        }

        public String getCorrespRec() {
            return CorrespRec;
        }

        public void setCorrespRec(String correspRec) {
            CorrespRec = correspRec;
        }

        public String getTelCorrespRec() {
            return TelCorrespRec;
        }

        public void setTelCorrespRec(String telCorrespRec) {
            TelCorrespRec = telCorrespRec;
        }

        public String getEntVis() {
            return EntVis;
        }

        public void setEntVis(String entVis) {
            EntVis = entVis;
        }

        public String getTelEntVis() {
            return TelEntVis;
        }

        public void setTelEntVis(String telEntVis) {
            TelEntVis = telEntVis;
        }

        public String getCorrespVis() {
            return CorrespVis;
        }

        public void setCorrespVis(String correspVis) {
            CorrespVis = correspVis;
        }

        public String getTelCorrespVis() {
            return TelCorrespVis;
        }

        public void setTelCorrespVis(String telCorrespVis) {
            TelCorrespVis = telCorrespVis;
        }

        public String getNumRec() {
            return NumRec;
        }

        public void setNumRec(String numRec) {
            NumRec = numRec;
        }

        public String getNumVis() {
            return NumVis;
        }

        public void setNumVis(String numVis) {
            NumVis = numVis;
        }

        public String getScRec() {
            return scRec;
        }

        public void setScRec(String scRec) {
            this.scRec = scRec;
        }

        public String getScVis() {
            return scVis;
        }

        public void setScVis(String scVis) {
            this.scVis = scVis;
        }

        public String getFdmeRec() {
            return fdmeRec;
        }

        public void setFdmeRec(String fdmeRec) {
            this.fdmeRec = fdmeRec;
        }

        public String getFdmeVis() {
            return fdmeVis;
        }

        public void setFdmeVis(String fdmeVis) {
            this.fdmeVis = fdmeVis;
        }

        public String getPenRec() {
            return penRec;
        }

        public void setPenRec(String penRec) {
            this.penRec = penRec;
        }

        public String getPenVis() {
            return penVis;
        }

        public void setPenVis(String penVis) {
            this.penVis = penVis;
        }

        public String getForfRec() {
            return forfRec;
        }

        public void setForfRec(String forfRec) {
            this.forfRec = forfRec;
        }

        public String getForfVis() {
            return forfVis;
        }

        public void setForfVis(String forfVis) {
            this.forfVis = forfVis;
        }

        public String getArb1Sifle() {
            return arb1Sifle;
        }

        public void setArb1Sifle(String arb1Sifle) {
            this.arb1Sifle = arb1Sifle;
        }

        public String getArb2Sifle() {
            return arb2Sifle;
        }

        public void setArb2Sifle(String arb2Sifle) {
            this.arb2Sifle = arb2Sifle;
        }

        public String getSecretaire() {
            return secretaire;
        }

        public void setSecretaire(String secretaire) {
            this.secretaire = secretaire;
        }

        public String getChronometreur() {
            return chronometreur;
        }

        public void setChronometreur(String chronometreur) {
            this.chronometreur = chronometreur;
        }

        public String getRespSalle() {
            return respSalle;
        }

        public void setRespSalle(String respSalle) {
            this.respSalle = respSalle;
        }

        public String getTuteurTable() {
            return tuteurTable;
        }

        public void setTuteurTable(String tuteurTable) {
            this.tuteurTable = tuteurTable;
        }

        public String getEtat() {
            return Etat;
        }

        public void setEtat(String etat) {
            Etat = etat;
        }

        public String getDateArrivee() {
            return DateArrivee;
        }

        public void setDateArrivee(String dateArrivee) {
            DateArrivee = dateArrivee;
        }
    }
}

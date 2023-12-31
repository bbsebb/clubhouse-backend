package fr.hoenheimsports.service.game.mapper;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import fr.hoenheimsports.dto.game.importFromCsv.ImportCsvGameDTO;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class CSVFIleMapperImpl implements CSVFileMapper {
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
    public List<ImportCsvGameDTO> toImportCSVGameDTO(InputStream fileStream) throws FileException, FileDataException {
        List<List<String>> csvLines = this.parseToCSVLines(fileStream);
        if (csvLines.isEmpty()) {
            throw new FileDataException("csv file is empty");
        }

        List<String> header = csvLines.remove(0);
        if(!header.equals(headerPlayed) && !header.equals(headerNoPlayed) ) {
            throw new FileDataException("csv data header hasn't the required format");
        }

        List<ImportCsvGameDTO> importCSVGameDTOs = new ArrayList<>();
        for (List<String> csvLine:csvLines) {
            importCSVGameDTOs.add(new ImportCsvGameDTO(header,csvLine));
        }

        return importCSVGameDTOs;
    }

    private List<List<String>> parseToCSVLines(InputStream fileStream) throws FileDataException, FileException {

        try (InputStreamReader reader = new InputStreamReader(new BOMInputStream(fileStream))) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
                return new ArrayList<>(csvReader.readAll().stream().map(Arrays::asList).toList());
            }
        } catch (IOException ioe) {
            throw new FileException("file isn't readable ");
        } catch (com.opencsv.exceptions.CsvException e) {
            throw new FileDataException("file isn't in a CSV's format");
        }
    }
}

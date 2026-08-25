package dev.plesiosaur.persistence;

import dev.plesiosaur.model.Vault;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.IOException;

public class PersistenceEngine {

    public void saveAs(Vault vault, File file) {
        try {
            file.createNewFile(); // Don't care if it was new or exists
            VaultRecord vaultRecord = new VaultRecord(vault);
            marshall(vaultRecord, file);
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        }

    }

    private void marshall(VaultRecord vaultRecord, File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(VaultRecord.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(vaultRecord, file);
    }

}

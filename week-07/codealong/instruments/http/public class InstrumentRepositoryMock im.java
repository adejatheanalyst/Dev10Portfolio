public class InstrumentRepositoryMock implements InstrumentRepository {

        public List<Instrument> findAll() throws DataAccessException{
            return List.of();
        }
    
        public List<Instrument> findByType(InstrumentType instrumentType) throws DataAccessException{
            return List.of();
        }
        // Other find-bys?
    
        public Instrument findBySerialNumber(String serialNumber) throws DataAccessException{
            return null;
        }
    
        public Instrument add(Instrument instrument) throws DataAccessException{
            return null;
        }
    
        public boolean update(Instrument instrument) throws DataAccessException{
            return false;
        }
    
        public boolean deleteBySerialNumber(String serialNumber) throws DataAccessException{
            return false;
        }
    
}

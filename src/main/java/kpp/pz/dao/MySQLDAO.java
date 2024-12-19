package kpp.pz.dao;

import kpp.pz.hierarchy.Book;
import kpp.pz.hierarchy.PrintedMaterial;
import kpp.pz.hierarchy.ScienceMagazine;
import kpp.pz.myList.MyListImpl;

import java.sql.*;

public class MySQLDAO implements IMyDAO<PrintedMaterial> {

    private static final String INSERT_PRINTED_MATERIAL = "INSERT INTO PrintedMaterial (title, publisher, page_count) VALUES (?, ?, ?)";
    private static final String INSERT_BOOK = "INSERT INTO Book (id, author, genre) VALUES (?, ?, ?)";
    private static final String INSERT_MAGAZINE = "INSERT INTO Magazine (id, issueNumber, genre) VALUES (?, ?, ?)";
    private static final String INSERT_SCIENCE_MAGAZINE = "INSERT INTO ScienceMagazine (id, scientificField, peerReviewed) VALUES (?, ?, ?)";

    private static final String UPDATE_PRINTED_MATERIAL = "UPDATE PrintedMaterial SET title = ?, publisher = ?, page_count = ? WHERE title = ?";
    private static final String UPDATE_BOOK = "UPDATE Book SET author = ?, genre = ? WHERE id = (SELECT id FROM PrintedMaterial WHERE title = ? LIMIT 1)";
    private static final String UPDATE_MAGAZINE = "UPDATE Magazine SET issueNumber = ?, genre = ? WHERE id = (SELECT id FROM PrintedMaterial WHERE title = ? LIMIT 1)";
    private static final String UPDATE_SCIENCE_MAGAZINE = "UPDATE ScienceMagazine SET scientificField = ?, peerReviewed = ? WHERE id = (SELECT id FROM PrintedMaterial WHERE title = ? LIMIT 1)";

    private static final String FIND_PRINTED_MATERIAL_BY_TITLE = "SELECT id FROM PrintedMaterial WHERE title = ?";

    private static final String DELETE_PRINTED_MATERIAL = "DELETE FROM PrintedMaterial WHERE id = ?";

    private static final String SELECT_BOOK_BY_AUTHOR = "SELECT pm.id, pm.title, pm.publisher, pm.page_count, b.author, b.genre " +
            "FROM PrintedMaterial pm JOIN Book b ON pm.id = b.id WHERE b.author = ?";

    private static final String SELECT_SCIENCE_MAGAZINES_BY_SCIENTIFIC_FIELD = "SELECT pm.id , pm.page_count, pm.publisher, pm.title, " +
            "m.issueNumber, m.genre, sm.scientificField, sm.peerReviewed " +
            "FROM printedmaterial pm JOIN magazine m ON pm.id = m.id JOIN sciencemagazine sm ON m.id = sm.id WHERE sm.scientificField = ?";

    private static final String SELECT_BOOK_BY_TITLE = "SELECT pm.id, pm.title, pm.publisher, pm.page_count, b.author, b.genre " +
            "FROM PrintedMaterial pm JOIN Book b ON pm.id = b.id WHERE pm.title = ?";
    private static final String SELECT_SCIENCE_MAGAZINES_BY_TITLE = "SELECT pm.id , pm.page_count, pm.publisher, pm.title, " +
            "m.issueNumber, m.genre, sm.scientificField, sm.peerReviewed " +
            "FROM printedmaterial pm JOIN magazine m ON pm.id = m.id JOIN sciencemagazine sm ON m.id = sm.id WHERE pm.title = ?";

    private static final String SELECT_ALL_BOOKS = "SELECT pm.id, pm.title, pm.publisher, pm.page_count, b.author, b.genre " +
            "FROM PrintedMaterial pm JOIN Book b ON pm.id = b.id";
    private static final String SELECT_ALL_SCIENCE_MAGAZINES = "SELECT pm.id , pm.page_count, pm.publisher, pm.title, " +
            "m.issueNumber, m.genre, sm.scientificField, sm.peerReviewed " +
            "FROM printedmaterial pm JOIN magazine m ON pm.id = m.id JOIN sciencemagazine sm ON m.id = sm.id";

    private final Connection connection;

    public MySQLDAO() throws SQLException {
        this.connection = Connector.createConnection();
    }

    @Override
    public void add(PrintedMaterial entity) throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setAutoCommit(false);
        try {
             if (entity instanceof Book) {
                addBook((Book) entity);
            } else if (entity instanceof ScienceMagazine) {
                 addScienceMagazine((ScienceMagazine) entity);
             } else {
                throw new UnsupportedOperationException("Unsupported entity type");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void addBook(Book book) throws SQLException {
        try (PreparedStatement pmStatement = connection.prepareStatement(INSERT_PRINTED_MATERIAL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement bookStatement = connection.prepareStatement(INSERT_BOOK)) {

            pmStatement.setString(1, book.getTitle());
            pmStatement.setString(2, book.getPublisher());
            pmStatement.setInt(3, book.getPageCount());
            pmStatement.executeUpdate();

            ResultSet keys = pmStatement.getGeneratedKeys();
            if (keys.next()) {
                int generatedId = keys.getInt(1);
                book.setId(generatedId);

                bookStatement.setInt(1, generatedId);
                bookStatement.setString(2, book.getAuthor());
                bookStatement.setString(3, book.getGenre());
                bookStatement.executeUpdate();
                System.out.println("Book added with ID: " + generatedId);
            }
        }
    }

    private void addScienceMagazine(ScienceMagazine scienceMagazine) throws SQLException {
        try (PreparedStatement pmStatement = connection.prepareStatement(INSERT_PRINTED_MATERIAL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement mStatement = connection.prepareStatement(INSERT_MAGAZINE);
             PreparedStatement smStatement = connection.prepareStatement(INSERT_SCIENCE_MAGAZINE)) {

            pmStatement.setString(1, scienceMagazine.getTitle());
            pmStatement.setString(2, scienceMagazine.getPublisher());
            pmStatement.setInt(3, scienceMagazine.getPageCount());
            pmStatement.executeUpdate();

            ResultSet keys = pmStatement.getGeneratedKeys();
            if (keys.next()) {
                int generatedId = keys.getInt(1);
                scienceMagazine.setId(generatedId);

                mStatement.setInt(1, generatedId);
                mStatement.setInt(2, scienceMagazine.getIssueNumber());
                mStatement.setString(3, scienceMagazine.getGenre());
                mStatement.executeUpdate();

                smStatement.setInt(1, generatedId);
                smStatement.setString(2, scienceMagazine.getScientificField());
                smStatement.setBoolean(3, scienceMagazine.getPeerReviewed());
                smStatement.executeUpdate();

                System.out.println("Science magazine added with ID: " + generatedId);
            }
        }
    }

    @Override
    public void update(PrintedMaterial entity) throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);
        try {
            if (entity instanceof Book) {
                updateBook((Book) entity);
            } else if (entity instanceof ScienceMagazine) {
                updateScienceMagazine((ScienceMagazine) entity);
            } else {
                throw new UnsupportedOperationException("Unsupported entity type");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void updateBook(Book book) throws SQLException {
        try (PreparedStatement pmStatement = connection.prepareStatement(UPDATE_PRINTED_MATERIAL);
             PreparedStatement bookStatement = connection.prepareStatement(UPDATE_BOOK)) {

            pmStatement.setString(1, book.getTitle());
            pmStatement.setString(2, book.getPublisher());
            pmStatement.setInt(3, book.getPageCount());
            pmStatement.setString(4, book.getTitle());
            pmStatement.executeUpdate();

            bookStatement.setString(1, book.getAuthor());
            bookStatement.setString(2, book.getGenre());
            bookStatement.setString(3, book.getTitle());
            bookStatement.executeUpdate();

            System.out.println("Book updated with title: " + book.getTitle());
        }
    }

    private void updateScienceMagazine(ScienceMagazine scienceMagazine) throws SQLException {
        try (PreparedStatement pmStatement = connection.prepareStatement(UPDATE_PRINTED_MATERIAL);
             PreparedStatement mStatement = connection.prepareStatement(UPDATE_MAGAZINE);
             PreparedStatement smStatement = connection.prepareStatement(UPDATE_SCIENCE_MAGAZINE)) {

            pmStatement.setString(1, scienceMagazine.getTitle());
            pmStatement.setString(2, scienceMagazine.getPublisher());
            pmStatement.setInt(3, scienceMagazine.getPageCount());
            pmStatement.setString(4, scienceMagazine.getTitle());
            pmStatement.executeUpdate();

            mStatement.setInt(1, scienceMagazine.getIssueNumber());
            mStatement.setString(2, scienceMagazine.getGenre());
            mStatement.setString(3, scienceMagazine.getTitle());
            mStatement.executeUpdate();

            smStatement.setString(1, scienceMagazine.getScientificField());
            smStatement.setBoolean(2, scienceMagazine.getPeerReviewed());
            smStatement.setString(3, scienceMagazine.getTitle());
            smStatement.executeUpdate();

            System.out.println("Science magazine updated with title: " + scienceMagazine.getTitle());
        }
    }

    @Override
    public void deleteByTitle(String title) throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        connection.setAutoCommit(false);
        try {
            try (PreparedStatement findIdStatement = connection.prepareStatement(FIND_PRINTED_MATERIAL_BY_TITLE);
                 PreparedStatement deletePrintedMaterialStatement = connection.prepareStatement(DELETE_PRINTED_MATERIAL)) {

                findIdStatement.setString(1, title);
                ResultSet resultSet = findIdStatement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");

                    deletePrintedMaterialStatement.setInt(1, id);
                    deletePrintedMaterialStatement.executeUpdate();

                    System.out.println("Deleted material with title: " + title);
                } else {
                    System.out.println("No material found with title: " + title);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Book getBookByTitle(String title) throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_BY_TITLE)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("publisher"),
                        resultSet.getInt("page_count"),
                        resultSet.getString("author"),
                        resultSet.getString("genre")
                );
            }
        }
        return null;
    }

    @Override
    public ScienceMagazine getScienceMagazineByTitle(String title) throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SCIENCE_MAGAZINES_BY_TITLE)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ScienceMagazine(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("publisher"),
                        resultSet.getInt("page_count"),
                        resultSet.getInt("issueNumber"),
                        resultSet.getString("genre"),
                        resultSet.getString("scientificField"),
                        resultSet.getBoolean("peerReviewed")
                );
            }
        }
        return null;
    }

    @Override
    public MyListImpl<PrintedMaterial> getBooksByAuthor(String authorName) throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        MyListImpl<PrintedMaterial> booksByAuthor = new MyListImpl<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_BY_AUTHOR)) {
            statement.setString(1, authorName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    booksByAuthor.add(new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("publisher"),
                            resultSet.getInt("page_count"),
                            resultSet.getString("author"),
                            resultSet.getString("genre")
                    ));
                }
            }
        }
        return booksByAuthor;
    }

    @Override
    public MyListImpl<PrintedMaterial> getScienceMagazinesByScientificField(String scientificField) throws SQLException{
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        MyListImpl<PrintedMaterial> scienceMagazinesByScientificField = new MyListImpl<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SCIENCE_MAGAZINES_BY_SCIENTIFIC_FIELD)) {
            statement.setString(1, scientificField);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    scienceMagazinesByScientificField.add(new ScienceMagazine(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("publisher"),
                            resultSet.getInt("page_count"),
                            resultSet.getInt("issueNumber"),
                            resultSet.getString("genre"),
                            resultSet.getString("scientificField"),
                            resultSet.getBoolean("peerReviewed")
                    ));
                }
            }
        }
        return scienceMagazinesByScientificField;
    }

    @Override
    public MyListImpl<PrintedMaterial> getAllBooks() throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        MyListImpl<PrintedMaterial> allBooks = new MyListImpl<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BOOKS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                allBooks.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("publisher"),
                        resultSet.getInt("page_count"),
                        resultSet.getString("author"),
                        resultSet.getString("genre")
                ));
            }
        }
        return allBooks;
    }

    @Override
    public MyListImpl<PrintedMaterial> getAllScienceMagazines() throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        MyListImpl<PrintedMaterial> allScienceMagazines = new MyListImpl<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SCIENCE_MAGAZINES);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                allScienceMagazines.add(new ScienceMagazine(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("publisher"),
                        resultSet.getInt("page_count"),
                        resultSet.getInt("issueNumber"),
                        resultSet.getString("genre"),
                        resultSet.getString("scientificField"),
                        resultSet.getBoolean("peerReviewed")
                ));
            }
        }
        return allScienceMagazines;
    }
}






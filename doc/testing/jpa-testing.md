# @DataJpaTest  
Spring Boot provides the @DataJpaTest annotation. We can just add it to our unit test and it 
will set up a Spring application context:

````java
@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
"spring.jpa.hibernate.ddl-auto=create-drop",
"spring.liquibase.enabled=false",
"spring.flyway.enabled=false"
})
class PersonRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(personRepository).isNotNull();
    }

    @Test
    @Sql("createPerson.sql")
    void whenInitializedByDbUnit_thenFindsByName() {
        Person user = personRepository.findByName("Zaphod Beeblebrox");
        assertThat(user).isNotNull();
    }
````
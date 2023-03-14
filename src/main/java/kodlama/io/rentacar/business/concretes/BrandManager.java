package kodlama.io.rentacar.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.business.requests.CreateBrandRequest;
import kodlama.io.rentacar.business.requests.UpdateBrandRequest;
import kodlama.io.rentacar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentacar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentacar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentacar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentacar.entities.concretes.Brand;
import kodlama.io.rentacar.rules.BrandBusinessRules;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;

    // public BrandManager(BrandRepository brandRepository) {
    // this.brandRepository = brandRepository;
    // } //@AllArgsConstruter ile oluşturmuş olacağız

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        // List<GetAllBrandsResponse> brandsResponses = new ArrayList<GetAllBrandsResponse>();

        // for (Brand brand : brands) {
        //     GetAllBrandsResponse responseItem = new GetAllBrandsResponse();
        //     responseItem.setId(brand.getId());
        //     responseItem.setName(brand.getName());
        //     brandsResponses.add(responseItem);
        // }

        List<GetAllBrandsResponse> brandsResponses=brands.stream()
             .map(brand->this.modelMapperService.forResponse().map(brand,GetAllBrandsResponse.class)).collect(Collectors.toList());
        return brandsResponses;
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {

        // Brand brand= new Brand();
        // brand.setName(createBrandRequest.getName());

        this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());

        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);

        this.brandRepository.save(brand);
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
       
        Brand brand =this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        this.brandRepository.save(brand);

    }

    @Override
    public void delete(int id) {
      this.brandRepository.deleteById(id);
    }

    @Override
    public GetByIdBrandResponse getById(int id) {
       Brand brand=this.brandRepository.findById(id).orElseThrow();

       GetByIdBrandResponse response
       = this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);

       return response;
    }

}

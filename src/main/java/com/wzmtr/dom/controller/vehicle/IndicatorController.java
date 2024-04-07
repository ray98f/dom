package com.wzmtr.dom.controller.vehicle;

import cn.hutool.core.date.DateUtil;
import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.IndicatorService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 车辆部-重要指标
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:19
 */
@RestController
@RequestMapping("/vehicle/indicator")
@Api(tags = "车辆部-重要指标")
@Validated
public class IndicatorController {

    @Autowired
    private IndicatorService indicatorService;

    public static void main(String[] args){

/*        String[] eipTodo = {"69a6c635-c989-4a5f-9394-4c7209a369ad","1f8811ed-aa29-49e4-a55f-ac59227e0015698bc31d-4711-4ad3-814f-470988e0a444","a9aa5000-3d26-4bfc-94c1-c31f785fa02e705ed9a1-cf6c-479a-bc62-1a5d7cf98730","e083d76d-5b7b-445c-839a-703828ca8eebdaff76a5-0415-4c77-b597-b38d1d0ff5df","31a9f782-dfab-486c-8b54-56f8190e3c1fb3c6972c-2efb-4178-b9d7-51c3c056abd2","25a2e0dc-070a-4a4f-8289-cbe4ac019f9c","59e1e539-3e50-49c6-b6c4-6fed1dd01d99","1e517f1d-c302-4e46-b012-5607cf5a6f05fae31161-77a1-4687-ae47-0e1cba8b92dc","db17be4f-003e-451a-8aca-109d08f7948d7765a697-4829-443d-bce8-9fd4eefaeef4","c4137f8e-3ce3-46c2-96d5-3114a6fd31a73509dd4d-c1f7-4f2d-82a3-f8b432c010e2","088925b8-49b6-408b-9ee3-5202622e1b22","39492f2d-6f47-43d2-a672-308e3f9927c68b20c102-6325-45a4-bdff-04d195c3820c","83461f9e-476f-4d83-8d11-698043fc8428e5cd413b-2268-4d2c-8a1c-2f9644bf74c8","a4d93d9d-15eb-4347-b4f0-e6675c45442b87f5ac0f-46c7-4e2a-8428-ee602782c3a9","e92fbeb3-9d05-4bc8-9977-aaccad5dde8b5e784194-4f1d-4901-b918-8f83ccb5ecbb","dabaa18c-ff45-4805-b513-6fb686963d247736d3ec-d87e-4b76-a640-6b5308dcaf92","1355d437-3583-4354-bfad-f41485a9c93d044351a4-3138-46cb-8a8d-bef1f8f7da64","d6e3195d-e15c-4a1a-8b61-00d243a4cc42","f78f70e8-fc91-427a-88df-ec15ee001808","90841620-6994-44c0-adaa-38bebf8bee09527262f5-4744-4a1c-b756-1882e58ffee9","1ec105f5-4f3a-428d-8ce9-30661d84c90527604c9b-272e-4fda-92b2-d047addb05de","fe7778c2-461b-41f6-9605-41d275383b185e87ba96-a3b3-4761-bd90-181eabf428d4","16695185-9732-4390-a98a-228876b1ab439c586b5f-36c5-47d1-8ae2-d9f19e9ef21e","f08bfe40-65f1-460c-bbbc-298cc8280ec16eab1aae-9f5c-433d-abef-2c12b1b20aaf","aadaf06a-459d-4ed3-b229-417340e37e77","a536eb52-9083-46b0-9d57-baa13916203b","0467d879-79f1-4a51-a0e4-83ed4bfb02ca","6cbfbd59-b7b8-4d40-9ffe-c701354dfcf9","5965f52d-85c6-479b-8b27-39b5f060a780","b2cb3464-d7fe-4c8f-aa88-7ccfb14ef7ce8e2bb4d5-ec2d-4053-ad5d-578fbc446318","4991cd31-fb26-476f-b421-12f9638fdf1a902217e4-05db-4408-8ec4-6e93a7527060","270d42a1-6a85-48bb-9aaf-656f5688ac4436180a6b-02f0-42dd-8034-a9f229742b0c","5fa0ce86-10ff-49fb-917d-f3b1f5de3731","22ba6cd8-5533-4a89-89e5-ef47c3bfb9e5","46036644-c997-445c-8510-58352ed09670","2da5db14-a992-47c5-b14f-cbcefd9146fff32e48dc-718b-480e-94bc-3759c8af58b8","2da5db14-a992-47c5-b14f-cbcefd9146ff274ae91e-1b08-4dee-84ee-5b2b9338e7df","b71f49d6-e82f-461c-a3a0-f0aeb237f00f6a6aaf98-e42a-4a84-a185-11981dd40f81","7be1dd09-e0cf-4ba9-9fb7-b0fb6b5de9b3d2b96c0f-b315-4b63-9a97-401b57f5c071","9991851f-5ec6-4839-b5ec-ad2c31d1ddf603d71996-22b7-42de-a6e5-1f7b3ed42487","76f7de25-1c98-4bf9-b631-fd5eb8a87d4cf1417a1c-5812-4837-91fb-76af8822936b","532eb757-2dcd-4b9c-a9b2-2ba5672e67ec","9f4db2ce-f0aa-4ade-ba71-558d0868190b","493e038e-db78-4e9d-a9eb-40d3662540cb","e9073713-6037-4e46-baaa-7c7f07e460b7","4436fac0-0a51-401d-bac4-367142fb6fbb","5756b22e-8cdf-424d-a2e5-80688feba6c8","ebbef1d4-b2fd-4aea-af5c-dc8112fff612","b4b9a277-f834-418c-a56c-bb461f2fba1d","309c404c-2dc2-465f-8531-adbe52af8bf5","1df95df2-d791-45ba-894e-daf30ea12243ffc455e7-6422-43d9-8a9a-43405ef6fb2c","1e2c9417-21c8-4ed1-a11c-d0eb5d10d66ed1e2042a-0983-4847-9f41-6897de89f6a0","16022ffc-04da-4d99-a6e0-0c799b6f7b816810314d-02a1-4c5f-a4da-5f23e2162a51","a3e33b5c-3244-4d8a-a847-b6f9a25f1bfc7f63c5a6-46b0-4edd-ab82-1eaf4d39774c","fb62d2bb-5a1f-4ac8-b065-0e14f32d2babafad452c-77bd-47ef-9d9f-da63c451ae0b","c683ec22-5b1f-4a17-8cb8-7de54ac826ec57cb8d81-4769-4070-aff0-72e7104147e4","c0721f2d-c2e0-41c2-9b1f-0b3c12020129a1b820a3-1bcc-4650-a9e3-8fb1f8e32010","d97f6331-1e4a-42c6-9f08-d2aa26ded3959e07ca0f-45d7-41fd-b672-ecbbd7e47728","08a7e5ca-1446-40ac-ad24-402235682fb0e94bb05f-0b81-4987-9ff3-ac14f88554c9","911fe6c7-428e-4f41-8829-97ffbae6c9e7","d6ec8332-e34f-4123-9f64-d7440996a7bc7b514e8d-b079-4cfc-9605-28dc95e70022","01529c97-6bba-4979-b19d-916bc5a851405c4ae4d6-93a6-41e1-8d9c-c6cbe0780f77","cfcf7a1c-b950-4e1a-91bc-43f3d0f8c352cc333bc2-ba95-4b49-8e9f-303824c93b93","5f6ddb7b-1df0-4d6f-ab2c-880104f32774","ad62e447-b1f9-42ea-9951-c25b0be440e1","4e842f1d-1177-4b12-9062-0a7ddc4cba78","c20327e1-8d73-4140-906f-bf0d442a604d","134bfa6b-b152-49ad-b306-e958308682dc","84e5417f-5381-4dd5-ae16-44dc1b0fe83e","3ad9bb54-e675-4200-b04a-0742ed203fc7","81a5bee0-be5e-4b46-b40b-797992002c66","b2ae06a3-7007-438f-baa7-cdfdded03a6a","c0f1cadf-9e1a-49ec-a9f0-999a3bd6aa67","8e1eed19-83ec-4394-ac3e-5d90a469a200","3eb9b1f5-7f87-4137-adac-cfa0c9546eaa","c2b9eca9-10f5-46ba-b22d-dcfbf4d79d43","f0afad63-5bba-437b-be48-a1fba12d6f27","9ee5d009-046a-4ee1-83dd-534207811907be3e9cb3-311c-49ea-bac6-20cc0fab2a42","a01aadb8-714e-4ebb-9db9-21ae7e2da52bd7fd627f-2e46-4ad3-8c7d-db6add7d5ba6","6681062","001d9c19-a4e6-4b1f-b0c0-5d21be861b19","8520c852-71fe-4aee-b04d-e37210044c1033264bd1-c93a-4b37-b1a5-09d04380a441","04410254-b947-4f39-a6ad-cd5c60a040a790dce12e-55a9-4f47-a354-0b56ea75907b","c71b9531-9252-4b47-9d6e-54ab50fddb2f","9436f25c-823c-4e5b-8545-faa8db798e5a","881e8ba2-f958-45ea-a852-730f66e4915df7d5eb79-42e4-40cd-a64e-03e26c769e20","7e42866b-25ea-48fb-afd3-3d9d0c73e7ad5956da8f-7eb2-4953-9fe3-1f26ea3b5dcf","d14f044f-83da-4ad3-a6d2-cea29d286a2dce9dad02-8ce9-49dc-8b1b-6f5d9a0bdf52","5bed62af-b8a8-492f-aeea-e2277b83b0401351b75b-2fd2-455b-bf96-540ed5c14fbf","e496e187-f510-49ec-bbfe-844841aa67c20ee0a626-dd53-4692-ae25-8578aa6de7ab","f7ebb5c1-e8c7-4cf1-8402-a9e7e7ad2929","9ada059f-013d-4838-879e-dacffade4a15","1e178624-75a5-410a-988b-82e764374dd7","f54f1e2f-f031-4049-adc2-fbc5c6ae6f1f","5b7d4525-d60c-4c5a-939a-fe8df38d903d","cad38165-08dc-4a25-8ecd-d327cbe937f8","e419d2df-6bd4-445f-8ab4-dea019279920","841fc2cb-5a41-4bd5-a2cf-6b74c623bcf7","258bafa8-7f59-45da-b70d-d1d6294dbffb","3dd848b5-a542-41c5-b2bb-e24b306810e8","7788b2a7-36ea-4117-b525-184a38359095","e54af796-bf21-400d-b85b-9b02865dad0078d4544f-2f66-4005-b602-e7d968fa4503","59076788-b210-4089-b898-e7077769561e6509d623-0aba-48f2-8d4b-2b63b0489593","bd6b41d7-c82d-40b7-ac73-21b19963d5d9","ecf4ef87-0e4a-497d-8811-0de5cb8fa463","657202f0-8097-477b-95e3-daf9f1fb8a2fd3c6c0b2-db09-4340-9e12-1c481602ccfe","55b13692-63ae-4528-bebd-0b3227e6dc3949803321-600a-49c1-a9d2-d024ba2d60e2","f7de6fb9-448c-4a0e-9bce-89520bde12410db55d61-8ece-4576-a2ba-0d7150cc1630","7a0fd802-c5b0-4cfe-8593-6fe1b2008cc8","b85ba04b-e6ce-43cb-8308-3ced5990a3fc639c6e07-d812-42c9-a970-bd0c60ceb2d0","2d7d3200-c7ec-44a1-b2f5-3a0789e2aa6d","169e5b6d-e461-47c9-ad38-3fabbe110a96","57945e6b-a166-4d93-a088-593d60de1641","1cc3a7d8-997f-4e0b-a37a-6032b969fe7efcc4a273-56b1-4256-baec-39fc701c2a8c","a9d01bc6-0bb7-4937-bed2-0369a6376e31","eeb83004-27d3-422a-b2a2-e7bdb9879151","56e621b1-242d-4194-88d3-df9fa3863d74","f304a435-1b14-428a-9122-7ea860771f21","9fa1906b-090c-47a5-a1d8-61a13a4ccc68","4c05f44b-a664-47bb-a112-0c1a7935e1b2","3795e880-eded-47c2-a691-732cb461ca0d","c6dce5ef-f4e7-496b-bbcc-b4af6a67a3b3","6beed123-9235-45c9-be63-cb8881185a3efa0e4086-cf7f-4744-9627-414a890af0d3","4ec55d61-4469-4d88-a3b9-4e0d2a08b8fc35ad8d92-185f-4e4c-8a4f-911080c72e3c","4b5fa951-d6fe-4259-8dc1-374354777cf778f6184e-c838-463c-a8a2-2876d65bed9c","0bd220f2-7485-4b0b-85e7-6d86ca84cdeb","aa7badfd-43aa-46c3-988c-28b16e1042f12f635e45-2ef6-4a52-8da7-3e54227893ae","70101f57-87b8-43af-809d-732b2a570b1c","05f9ec01-b138-4270-86f2-02159851fa45","65afa251-3dfb-4976-95da-f8927d0fee05","926f711d-4e73-478a-ab20-4d9e860cc495b479d544-bc1a-45ca-9909-44ca2fd90c59","1fdbcff3-fa55-4641-a503-65b8d76eb705","6607d747-2966-4871-84bf-1b7b577711fc1a07a62f-2806-4e18-8be9-6ed907dcf630","cadaf294-dcbc-49f9-834f-7d18d6a06c7e","cfe7223b-482b-414a-9966-45649d43894fe724e023-f726-41ca-8b8e-fd0f3ddea48b","0e21fa53-23b3-4864-80f6-a17a268f856e","ca9542f6-a3ec-4861-800b-7952a41b8018c7d9ec77-cfa9-4f89-940e-1f51b677fac1"};
        String[] hrTodo = {"64b2162e-c0d7-49ae-9c47-d4ca7ee0eb25e86d1925-cbd2-4cbb-9c38-d7a651eb99ce","3a9f6699-7e6e-4ff1-b868-0849d0e4309af9e18cf1-477d-4d60-b85f-90057194bac2","3489355e-d663-4e92-8f7f-f9684e38f3e1aa991ec0-8a49-4e6a-9a24-cfa4e68181d3","feff6911-30e4-484c-b7a8-5b593e4f79aafa59096f-afeb-479e-a4e9-0765fc03d6a7","b5da7e91-5e59-4c2d-84e1-dc449f3035d6fd0eaef9-6552-4237-8473-4d92ad57730d","7ba1c099-d183-4cc5-bb16-a3155f185bede77e47c8-0992-460d-952e-d176f58fc575","048316e4-dc80-4d42-8133-d0891e0562bf498d1699-80fc-4adc-bce2-b2d6541f2a51","c013ab94-6cc9-4551-bca4-874e48252cb061afbad9-fb8c-445e-bd6d-2cd2dad68ec3","f5b36e5b-0a2d-4a3f-9caa-5a4060cd33efd3c716a3-e3b8-4556-8161-b64151a7932b","97455cc0-517f-4eda-8c5c-9711dbc73d0781708baf-0750-4c77-9d82-0804ef5892c1","9f94b4bc-4f0b-4864-a271-8a1146afc770265ba60b-b76b-437c-8234-afbd705233f3","a655e80c-0f9e-474b-a17a-30894ec5ce72f3b17d50-81c2-40c3-b89f-61f0feae3943","182bc89e-aa11-4806-aecb-773cfd5302d06c86a3c4-fadf-4aed-95cf-50819f33426d","3aaba8c9-f68f-4f90-bd8f-ff108a66d5d5f79ae1ef-5c77-4908-bb17-d6102dcb7cd7","0ea1a8f9-a3a6-4241-b51c-5dbb22759a58c9bd40bf-f2e2-4c0a-9a9b-2e7868262163","804ae961-6f35-4062-9460-ec1351853535f24bb4e3-7988-4dac-90e0-aa53da4d8637","c0d9c85f-9e82-4fbd-8147-7259135da93afcb07d31-62d2-410a-b520-4b05c798f2d6","a44b018a-d651-4ce0-bc9c-2dfada4787280a00f6da-35f7-4f43-87f2-6f3f84a44bf4","532c4759-ec89-4f4d-b12e-5275e064f9ff30a079f8-6014-41da-bf9f-9ad203120728","ab553192-9717-4fbc-8b7e-55ac8acedc6c2f40e780-a798-41ec-8262-6a050644d075","48dc0872-6b85-4d81-98fe-5b57bab0fafab99199e7-ce92-4972-8892-9518af493fd2","61a0603d-36db-4687-aedb-806572c7a4be886c01c1-3422-475c-a736-4a96f3ade84a","5471ac24-2066-4b8d-a2ae-edd63917d00e4820510d-7fe8-4028-b354-7a57a4826b7f","48f853a5-d3eb-43bb-84a6-4a246ed885dc181ad3e9-ac5f-4294-a200-75c1b04ac286","5a85b293-5332-44f8-94fb-cadf3b57e6966cc5a313-97ee-49bc-8a74-f4707019a022","8f7ea493-e1ef-426b-840a-cb851a05278c8e820fbb-35d9-4ea5-949c-6cf9c0bdc4d2","3a5bf339-44ce-4aa1-85ef-67b79c97b9b20fec47ec-4dde-4db0-abe7-32c18ba42402","ed8fb91d-1390-4f69-abc5-a787fb37c1175d135156-678a-4ef1-8d4d-af5162e404b8","96287242-b0a2-46b8-bac7-00ee4c34fbd6cb7aa44f-f567-4d52-b619-ee78c1eac363","80906a64-127f-4dbb-b013-74865525e54e5605ee15-ff6f-4cb7-9f7b-e3fe478337a1","2ae33bff-8e74-41f5-8aa5-74b48b8e3b52a1ebb75b-61a5-4ae3-ab27-866c119e6c0c","c55a2125-2632-47a6-b38e-b0950c01cb11c2392223-d915-4242-9735-f3e1c51d56da","42e749cc-7808-49ea-adf7-5a100a989721fda6f04c-7b52-4a98-a54d-a8cfa1e0e73c","789a2e4e-682e-4fd1-9067-1cc254ed2fad8f1c4b5e-6208-454e-a18e-0df86542f8e6","c87b943d-5a80-4bfc-97e0-82e4610bd6bf48a5f7e0-5c8f-4c7e-a138-94cb8ab3e281","4e0c4aa3-e834-4cba-813f-51c9d8c27dcc5c1f28dd-c67d-43ea-a742-3c386e72e763","e761b02b-e88f-403a-8e16-e5ebfa310dad9f1774e2-c2ab-45f6-8683-6b49ebd6f94a","80702c00-9305-4fe2-8e13-4c166b9b9b9047dcf254-40d0-4fd3-9057-58a062a3e48a","922298be-4bc1-46e0-92e0-9b58637244c4b6aa0faf-cddb-4a63-808b-96d7cfa54a3e","5adadeb6-a9f0-4e62-b406-e6a8af48ab572504ae4e-1d0b-46ee-8d88-cc654c47ab1b","5a2aee22-0f3b-4f8c-a215-472106e7c09ae51aa04f-be81-4b19-8227-4287f87231ff","22671187-4b8a-45c8-9f93-ee440250e73fe0d86089-6199-4735-93cf-63edd53ded59","5e44bbf5-a951-423e-b483-5e31736eaf53e5ac7501-efa6-4377-a5e7-8883db11a380","9ed35b4a-3a46-41bc-b1ea-c078e53d211d02e5859e-dee2-4a10-bdf2-3e53bdba6bc2","c9098528-e968-40d9-91c6-d63a7cd5626542e837da-8d20-46cd-9837-7bbc2f0cefe8","b8624293-10e0-491e-965d-22cb4c9945b22dea7b74-9f93-48b8-90ba-2c43221d44b8","9b50192a-50a0-4be4-9b63-b98463552a806d00425e-8a71-4c13-963c-9c6a2345b3f0","b35c12aa-fb4d-4275-975e-3f387bfdb7879817a488-423d-43da-8ec5-1561eacbac59","7a566f69-2900-4ee5-8a87-cbddd5800ebd496f2906-7f0c-4102-b4b7-a00911ed0a9a","e577ca7b-0f86-4ce8-8aa1-f9c0e806dd0e046b58c1-09cc-46d4-8b41-1240165b46e9","07bbc2ef-3ce4-4253-a847-09067749a6d97393dc9e-fcbe-4c98-8ef9-24028153111b","f8da25b3-67f1-4d6e-b4c5-777127e6ca3dea2fb813-3755-48f9-9105-f9463d5db288","eee4dbf2-6a94-46c3-9bc6-fa3680f568d1f409639b-cd3b-414b-ab45-40fd9ef924ec","2397db19-2e98-4bb6-9e4e-6eb127f9c63c089b1627-d901-4613-98f1-133d7b6c258d","2be56779-88a4-4376-b03f-da71ce6bc6772f334e09-5cc3-4463-82c7-c6e00544777c","54b6a89a-bcf6-48d5-b049-270458062366f3ff1b49-3f0f-451c-8fea-b2826e7ff1aa","a34f70cd-1f89-4c09-96a6-1e742bf8966e61ccea50-0e9e-489f-9141-b5a9b5ea06e5","f42c0dfc-c587-49de-8656-d19f35d8f86bd3e4da2f-1e07-4735-acf5-28fb9484719e","d38e9238-a39f-4ee3-a31a-a44b1a6a139935d7b5fc-99ba-4419-ba36-56ed56b7516d","d348b25c-a9e0-44c6-be41-5f09ee03960537021fd4-4140-47e2-9c5b-4e236d9d9d81","7cb65ce2-7368-4c78-af68-9ea4c9d7363a73bd987b-2d8f-46a2-bd78-2de1d3a2a5c2","16da0fa6-b1c4-4d0a-bd35-bcd7c9fdc5d58552ef7c-ab78-4279-a3f5-0e7ac63ef66b","a5eeb3ba-e4b6-4fab-b6b8-c32dc2499df1237ac6ff-6e46-4f11-a56c-0c840ba13642","728ae737-13a5-436b-8937-5e476c8809d8615bc1b5-f015-40b0-bc47-3507c0a3b314","f86a256b-1c9d-4bd7-b4d2-23c4703447bb339f85b2-95a3-437e-ab3f-1805d3f4c961","9038e528-93f6-4873-bb51-f1c8be6a0d934ac896ca-10eb-4475-9c25-352e76b01936","97b16caa-27fc-47b6-8779-d15584fa03b8f30c704c-c458-4efa-b46b-aa23ab4b7ae6","01f76b05-0bb6-477f-9787-294e6299741021768aee-7c7a-434d-b555-4a71904216cd","d153e816-b249-4677-b51a-d81b11c5a031899cbad8-4994-4b73-8f8d-59520c647af4","d3fa92f7-b82e-41c9-8256-ac8ffac240df0e8eada7-4fce-478f-9676-ac10097708b6","f3f27168-9d02-4a96-8108-90a930fa49021f31659a-393a-4926-a28f-4c172b1dc005","b1c3033b-6281-4056-83da-e3a682a481af98727f35-b355-4948-bf1a-a6d7e95ec8b1","54d9f000-ed1e-48b3-b578-e7b076501a72102c5ebd-35ac-416e-8100-7429ec580e38","346a42ee-f8da-4712-91b3-f7013d27757b28e04e81-5cb7-491b-a9aa-8235ca9285e2","a4edbe59-41c1-412c-94ba-6ee41aaeea265457eead-8f01-48c8-ae49-b531b62a22b4","1c17e73d-5462-49ea-adc8-ad1641d41a06efb50de6-418a-4550-8260-3c923fcc7272","9e3b6c3c-2211-46c5-ae33-07525248067f2760dec4-8446-46fa-b4b1-2576eb56d2ab","6a450864-be7c-4b94-93dc-f0361e09a84a6541d277-d267-47e4-b87c-a67b69ce6df2","e4c0139a-4067-4d67-a0e9-de117c3843e79959a2d2-2d15-47c9-9c90-19a0720d48a6","9ca5fbd8-baa4-421e-bf7a-312963d8b6313a235311-0718-462d-926c-1e4678a3a0dd","a1bc5c3c-83bd-4465-9df4-57742e8d57361b91d7d3-753f-42b5-83fc-8738a0410020","400c6a27-a143-469a-8b97-36067c325fca6a81a7eb-5c12-4f76-9fce-0ab2034e279d","22b0ccae-5315-4dd5-bcb2-21ebee16ac1256c93f0a-73af-4190-ad6f-11030f13df2c","d58eb581-5519-46a5-a8cc-6c12abe2017fb86bac70-8b57-469e-9164-cae44be3c370","192025ff-8677-4fdc-a417-36212646fd65cf48933e-84fe-42af-b9f3-26cd531ef375","cd659ac2-cb38-4e20-a6dd-d76064f9061b505dd60d-246e-4a6e-b0b7-7fc6fafd6537","c9caec6a-90b6-4388-a472-6bf3170f1baa6107756d-bf24-4880-a3b5-a84ca49a51ca","9f3cc296-23f1-4382-a1a4-f7731165bd6f63c51f26-ca82-4ec4-86cc-253111c8638c","a633237f-cde8-4612-bc16-efb2ccd25d4601e50175-1fd2-44c3-a184-e48f06717f04","b188a0f2-860d-4e43-bc54-411e6bb0aef638aaf5cc-f981-4d73-878a-8ccc6536ad1b","125de970-86ab-4405-876a-1113c3d1b737b1d59468-52f1-4b83-915b-d75e2444104b","a8ec241a-d684-458e-b63b-b200dc4d32f5f14edb34-64a3-451c-ba89-2ddf215feeb2","4e58cfea-e81b-4825-afba-c8534ea1785d232df9d9-f5d3-4c08-9410-ff73f209a3ba","0ee519a4-0f7a-47de-8d4f-b32dba102eddd68b8e1f-d043-436f-9c8e-6deccba629d2","1446a4e0-005e-41c5-b05d-275904b3b225fd54b8a8-ef89-452e-ad8a-0463ea5392bb","a7ca5e68-9ff4-4d62-b24d-cd365e00b5989b978db8-9de7-46c0-a453-409f4c59f8b9","a0fd81f2-5a29-4712-b0d4-653357d635d2bad49ca2-e036-4594-bb8d-846c717fa15a","8f50044f-4576-4d1d-87d1-f4afd3d27a60b76b4c85-143f-4828-a728-e5af920ea520","2c697723-9e36-4838-839e-9ea7f037de11045694d4-48a8-4687-8510-ac5e1515c772","bf5d5c9c-5a00-41d1-a605-1d59614ea98df9c42e05-8b16-47d3-a54e-fdfc40ccf4b3","e42b6b88-278d-4905-8b8e-7035cc04232e719a437b-00ae-4111-b796-75e3b3118813","5760dcc6-7225-4be2-9d4b-087d1e94fc59148888a4-9c2f-4492-b4dc-c83f00107f15","22259096-ae91-4c1f-b7f3-2f297689b057f710ff9e-6dae-47d2-ae3d-895c86e62bad","a8f69e2a-9e36-42a3-bc67-ff5c0dca6799adf2374d-ae66-4ee7-8770-57c17cbf03f5","4cc496ec-0c4f-41cf-93a5-a6b1f03a795741d7d9da-e472-4b16-95bf-0f892bd9e154","1db2ce1c-1003-45fe-a17a-546f1c17fe89ea42c6ad-c8af-451e-a629-c66184af7961","31161b53-12c8-4e7e-b790-b325e08cb263fdbbc5b3-c97a-49da-a823-d1f3b457d805","85e0f152-c481-4547-9e28-8dce80d7d1bce22eab77-173f-4114-ae43-f0089ff80459","8ce3b96a-9532-4098-be8e-e265b1e3073b14c89a5b-e37d-4f1f-89dc-b9cfb421f146","9d211e31-dd99-4e49-a49f-35a5ed26cc791cdc764b-75bd-4585-959b-b08f13594628","293b3dcd-aacf-4b9c-b8b9-e01db5030e99b76b7899-9812-491b-8dae-2143d6e32162","4dc746b9-1ddb-40b8-a45b-c386d3168c1999199e2d-3faa-465c-94c5-67176c935e88","cf11fd94-abf8-4234-8d37-e29d63852e1884eaab45-2125-457c-b39b-2ce5db9a290d","f127fb8c-74b2-4c72-a5bf-10ae558c9713b3cda9ad-aea5-40cd-9684-87579dbd2ebf","6c9f0fb0-2347-4a32-a578-7ba5145fb03f4afb0343-0267-46c6-b4d2-93551ca137f6","e4007612-5a43-4ccd-aacb-b1bc7d8a0389e048135a-dba9-40c4-aad3-d1934b74994f","6875599f-4171-43ff-9e21-3fdb704bde5b81e386d9-9685-4f7e-8b6b-de1c74a819e2","b71f49d6-e82f-461c-a3a0-f0aeb237f00f654948f2-5757-4e27-9466-3551c91bb2ff","ef28c1d1-869e-46e2-8e8b-fb690e525b63dbd4d070-fd3d-4165-9a22-d7bf6852e89e","77ecb059-1351-437d-b6f2-cea57cf70eca93b0b7e8-c583-4cae-a7ce-ee49efd20974","7a548bf0-34a0-4025-9aa0-0dc2ef9a279b4fe693fa-628e-494f-9af3-ba59e30954ae","c3896a75-04c0-4566-9c89-0455fc76795efb7eb716-fdd9-4811-8bed-b80c434a93ac","99ddd24b-b278-4f3b-a923-43f6bfc04f892b09c1aa-ba71-416a-af9e-c38d89bcc26e","70b5f7f4-4f19-4013-bf56-2624da98f325a87fc887-1ef2-4397-b6be-36d0678c1548","322d6772-ee4e-4efa-976b-f9c0efef796f5bb99d68-262b-4a2f-bd4c-95a15cd57644","e2133655-642a-4844-bd4f-664186bb0aed92209ec4-e488-4297-90d6-2ec49d2636ff","377ed4f0-4621-4c23-bfd8-e28f1df48d7ad24fc088-33e7-4654-b69b-b03831e8015c","de3eca73-bb53-4838-820f-5c1314586af6a69995b0-9c35-4d23-8307-5355ba27c865","d19b308b-7ab0-4bdf-a4ba-c1676031b03102764d23-bf83-4663-a750-113e11e2a9c3","a934446f-b527-4d6c-a0f7-3af4c8daf45b9ceda3b1-6d2a-47f7-826c-d6bdb6f5b5b9","2f981777-e87d-4fff-8b68-2e96291c54e98086ad5b-9c6d-4c92-8da7-4d30e76b50cc","cc7ad90f-37c1-4bcf-886e-e22e5874e816033f7db9-a739-42f7-996e-4324de2ad90d","10224b29-94ee-42d7-bfe8-4bcc7d66e70a593469d2-3954-4501-8da9-67afd7f5bf79","351459d3-459f-4a76-a51c-20fb240e75697b2fae1b-c86b-4e2e-8813-d42537ddab24","50d5bd79-ead2-49c1-a1e3-28203844978420cf0d71-b9f0-4152-ab86-8b9f248dfee8","86b701d2-cb6f-4a45-84f8-c9bf5893921254e7326f-c9f1-4cbb-8baa-0ea78ca94f05","649aa124-658c-4946-8309-c7155fde307cc51e3181-a9fe-44f5-a59f-ac089b1a9a5a","25f0c092-2b85-422e-9ca1-81e59696029464d6ded3-4ffb-43b8-ac99-989cb6ab942c","206fbe05-5a6a-4a56-b810-ac8e3da539bfe097886f-b0cb-478a-870f-822ff39b92c2","e9f4eaa5-5179-49a0-9e96-993d8dcee87809ca14ff-1562-487d-a4ee-1b113edca517","aec98195-dee8-46b0-8349-30abbd37895147dd2c83-245c-479b-be2c-da1ce1f24793","ebc8db8e-5ba5-41df-b644-a834cf444a393ebed35c-2668-4312-b51d-79685165bd42","6d7e4a80-a40d-44d0-9224-1d3c85ad28e10a88e7c5-bde4-42de-a1b9-e751cfeb238c","0ede7f82-ad5a-4fea-bed2-4c4de0bc22eff3e83d52-9981-4b31-a12c-67af53657f1e","60027d5a-b499-47cd-9ba8-ff1d81208d1eec39f7e8-7f92-45a6-8d14-58981055e4d6","0f4d1ae2-6b08-450f-836c-fa22b652bba5aa8fdafe-b4f0-457f-97da-863f5656b151","58022cb7-8b6f-4509-91f4-ad5bc270bb06c8fa076d-1f7a-43d3-ba4d-4f88593f930d","2a282c9b-eb68-4a0a-9047-8f0d93de33c704bf9cf2-759f-4140-b5df-4ed74c45a386","6dc72f62-215f-4483-a5e7-61297637871e3ce9bb08-ac6b-4f74-91a1-f943cccebc3b","08b3f0f7-1ddf-4dc7-9098-d41b6a0d35f95e192ad8-4231-4611-adc4-52dce3d3e3c0","6f03a788-8c2a-43d1-8751-c343739dca0bfe720655-96b4-4944-883b-1a08291ecf4c","b8ec9984-3b43-4e7c-ac7b-bd7395cfc2b33b632a1a-292c-411a-b9ac-8b5b6b4e3b58","68e64162-d6bf-4e7f-a262-1cac94d3b87e0be5f542-cf4a-4e23-a366-39e944b509e4","5c4a070b-f0ca-42a5-bdc4-7e229d34b833babd867c-2b0b-4501-8d55-707748b2ac3c","90bb2369-6ff0-41b6-bc76-ba93825a992e5362f389-bcfb-4797-b8d0-49747b0e365f","c3876fc5-e6ea-48bd-b7c1-d9e646e5cf432031a9bd-19d4-4dfe-972c-8921fee472a2","bf62ea43-4185-4e3d-a0f7-57d4b449b5bd655299d8-dedd-48e7-aa9d-38caea30ff28","eecdc4c4-821a-4f7b-b544-2bff35ff8e101a504c2e-d37b-47d7-8351-67be16d0597b","aa2ea5bb-deb9-4bce-8773-0de38c7b90bd8884825f-20af-43d1-8a2b-683c1ffb5de1","b6c2017f-3c7e-4676-bbc0-044524f3b65e6a5525cf-cd8a-4cce-b71d-0aa04ac8c564","0eb3e99b-9808-4634-9fff-297d9719d8f14e939493-3f61-4653-832f-1e78ece015b1","4a7527cb-55d4-4381-95b6-d49f0d4e7e0693e1f317-b09e-429f-bcb7-d6a2f814f98b","4ce2f82d-cebd-4ee8-a8ec-e0c5600449f37b0e80bc-83cb-439d-a0e1-18a93347198a","2bc45aac-405c-40c4-af37-fe812596f9248fb37e8c-75aa-4949-bf79-9c49e79b6a17","27834eb7-ef56-41ad-9723-f4400fcb27c2311e6e83-795c-4629-b1ff-d867a9946a80","c10913e0-1707-435d-aada-42834c7d9e82c34a1bc3-bf08-456c-a902-848d939a7de1","3949bae5-eb55-494d-b961-2574135d6ae7869eaebc-bd7f-46ce-9b0c-e77723e19e7c","8d8273df-f5dd-498c-b1c8-ff5adbcdd143aa310393-041b-4d3c-96db-bad652dbddcf","62e2df61-6f07-48f2-b0ac-413d5475eb0ff22d72f1-7ccc-46f5-b852-ee44e426098d","3471e064-9f05-4714-b306-6e3c3bba83e76dc7fdfb-13c7-4370-a183-d3ad384f3704","2acfcb3a-f213-4a88-8849-b5644294b3a806cb1387-9e77-4730-8865-93f939f218db","483c017e-3e02-49bf-870a-46e5995d743062f701e1-356e-44b2-b450-8041f411499c","aef22bb3-d3b6-4240-8c2d-a51617935be735f123df-d99d-4852-bdf3-9a8b2764d79a","bd29f63b-372a-4149-a249-92bde5edda6d429f3396-c2ad-42ad-8435-e50ed5f238e7","62eb3fde-0a01-4c3b-aa27-5d65d814be43ea464545-0c46-4b53-adc1-136de6a73c56","0f2b7ea4-902a-48a9-9ff0-5fd0483f365a828fe12f-e192-4bf1-83b6-233e30991ad5","a7970c85-d067-474f-900a-84a31202c5b18e3d3c9d-7917-4c69-a2a9-27f6f6557f46","90159129-ebd5-48ff-9e8c-1ae1b1c0e7492186010f-7c14-4c85-8858-ac92ae32596d","dc47dfad-a932-4bdb-8bbf-e759151c8fb87466419d-7a30-47ed-9b24-00cba68d48c6","f55dd9d5-9737-4fe1-b64e-c066b08f6a1cbb8f97e0-aadb-4ac2-a425-3ec571ec2ae2","4b7ea853-4318-45a0-9b47-7cfc96076c075f30e1b5-ad13-4c04-b45f-71396fee9bd6","6e2525bf-6f1a-4dd7-b029-2178fb84372a817bbdae-6e1f-4b5e-b850-a6394ac1e47b","34af2718-26b8-47ee-8efe-d8c41151e462cb3b11c9-7c68-4a8e-8e5d-a00a56da5a40","1bb4985e-5ad3-4281-b4f2-c366fc886589a247e715-c370-4a6c-99cf-d24109ef182c","ce8a2602-922f-4c98-bb5a-ff3c6e61c3ec6a9f2dea-e005-4ef7-b091-f6254644077d","7c4829b8-a216-45a5-9199-e308f10d4ad1d6d98541-cf06-4f3e-9ea6-145b16de3462","6b888cba-576c-4429-b6a5-3b78f0d2864db2687628-d85c-49bb-9adc-78c53603e9a0","f82e28fc-50a3-4ecf-a14d-c23cb925531454bdd725-2fd1-4ae6-a4a4-c3ea43685735","926f711d-4e73-478a-ab20-4d9e860cc495b479d544-bc1a-45ca-9909-44ca2fd90c59","aa7badfd-43aa-46c3-988c-28b16e1042f12f635e45-2ef6-4a52-8da7-3e54227893ae","2d3dd717-d91f-4136-95d5-ee467e3a489fff1a2c98-b07e-4f89-8478-f7eafc58808b","c978f1b3-757e-4869-a5a2-8b54e77510fe401c40c3-3e67-4498-b622-5ceed9b8b1f0","4686feae-eb28-447a-b60a-7615f512c3430acc83fa-f3a7-4ca0-9d22-9a25fc7f48c3","835a4011-45d0-4b7a-991a-28afdd0a8a48b2c606b2-83b7-4c59-a935-bfc4cbb36c4b","3f9533ca-24fa-421d-892d-c313d1823250528c2ffa-8724-4fad-9a5e-dbc7e8cc8550","d80513a7-bb41-463e-ac8a-a8839d1b737bb9da2600-7bb5-4565-af0b-9300b07fe334","31c8cf18-6f14-4f34-8c9f-231c066ec8c8bb533d6f-a714-45c5-99db-fd1402d4a192","c7695894-4bdc-4d74-9ad5-da61ce0457c8932b3ae9-fa42-42ff-802c-e94a5bf8601a","32981404-0bca-4a01-82d6-42dd7a7a62bbe5a8105c-d0f4-4abc-af6e-bb795f0db58a","51940b4f-4d10-43e9-a8d3-780f558bbbd18274a0fa-9ce8-4a7d-b259-a66644a9484f","82b2b06f-c88b-4d6a-a6e1-c6f44add7b372f4d0a33-26b3-4551-8567-8dfaf10ffa80","71a55c6b-5403-414c-9aef-7af9ff8edf40c053e82b-3528-4563-bb84-1b109ce29d75","d620f032-cc81-4ab9-9370-d4e7737691561ce3f637-fc89-4665-97db-1022fb0146f6","4a6096d0-2882-4b66-a336-2b70213e44a43bf24e5a-a2fd-47eb-927e-413c0505b3c2","6cd104ad-794b-439b-8d3a-6268087fc419e1fab4e8-1a5f-464d-99d3-a998ae0edcde","e9eb2dac-7737-4a49-9929-ed8b1f93caff5a89d2a6-b45a-4173-b9ff-02dfd2f78d06","f52b0e87-bc2d-4c39-947d-7ebe6e08791871d3c564-773d-4979-bb54-1de32d77a8b4","f10c4d44-1c21-49e8-a0a0-f5fdbb609e4da73d981c-9dd7-43d7-a6a5-8b4c75bc7cb1","d960f1ec-8a2b-4306-9d03-73782529eb07428bd39d-4572-4a26-b7b6-ce4ac5172a03","1a046b70-932f-45df-a7e2-31a5e9288b5e979cc2a1-a2b8-4588-9687-963d3d00196e","76303f14-7f4c-4a97-9b96-8c3e9d9f35e5388af042-d50e-4451-bd69-1c54b299e659","c086fc50-4e3a-4253-b41d-9d7a0978e8c0c7260869-4c16-477c-a7df-e8fd5f67b775","495f91ea-8c44-4641-921f-0a08c59fa1603288cb5d-cfbb-4906-9fdb-dda7f7733a10","e13826d2-f21a-4f8a-822d-298a45ff64ee4ec0c8f5-c24d-4d89-97a5-517710e78eed","5345bfbe-4a0e-4b0a-9827-5b246453a32d0969e815-2cb0-4482-9156-c0263a8cd926","aa9ddff8-2a70-4827-b4eb-e19250f29c0ec0a655aa-36fd-4c44-945a-4cc71f960a9a","168a31d2-4c8d-4a66-b9bf-cce81291a7772d6575cc-0389-44d6-947c-e837d015e7ee","7c6a07b6-ebbf-43d3-96df-d84086539445f21a34f4-f670-458b-b265-01e67c731bb4","26b8e1c6-effe-4d2a-8d4a-65320445d7afdddcc4dd-9201-4c14-8f20-4d9d76f9d599","ccf44ddf-2a11-4855-924b-434896e3738a07e812f1-12de-4e78-9baa-8be0e41de654","677d1e5f-b626-4ddd-86b9-70480d2650682ae513ef-7d92-41b5-b6c8-461313d77cdf","13670f3e-4b5e-4b1f-83e7-c4d49e523cd58f814f6e-0e14-41aa-8e1f-f1bb42b05573","bfeb8911-792c-46e7-9b7c-458284595738d46d7f45-08ca-4a43-a11c-f0b2328c8145","fd52a435-ec95-4422-a8ae-038b51dc68bdfc199c25-bd45-40e6-9b03-c49e524698ad","9032fdd2-fc3b-4824-a752-d08bd47437c41dddc1f1-5a91-4db6-9e74-1fc186003cac","dbb2960d-7a8f-4a60-9183-29c15d643d7d8ad98751-c69a-4014-bcc7-2ed211791e5b","20133d9e-4605-4e0b-84c0-68439dabc6e1be5ebdda-3f4d-4d3a-b61e-c514a46545d1","fe0e60bb-6598-4103-b2ba-078ea4ec30e67847e45c-f914-497a-a573-7b14b241d743","cfd31773-597b-481d-a979-f4fd3ef60f0bc8259a89-24c2-4d43-865c-ae4dca66472a","4f2bdc72-dec7-49bd-abba-0a37e55598dd85d8695f-dea4-474e-8f6e-b59d8d7cc7f1","b8256b92-9756-483c-887c-6256b66f1f2d4fccefc8-94c1-4358-8565-03b69ec19316","ca42c85b-0226-4b06-a08d-f741e9dc1dd35193968c-a3a6-4897-ab55-a3973a8c381a","e0a6c09c-fa37-4599-879d-cd8cdcf99e6fe923ccd2-6a9e-45fa-85fb-29ee81dd68e1","0cedbf7e-6542-4437-bc1c-ce66ea6a01e7aedb5149-2406-4c49-b3ae-03001bb82b57","eba16f16-0991-4d8f-a84e-f981af1c30b8ba354925-41fd-45b8-89ef-f89df5e17d40","dd8d3096-87fb-4c7d-b857-e8cb673d2ecb125f598e-dff5-404e-8fd6-eef8d5d9b5de","cdcee9c6-ee49-4c33-8149-b0300152af7f7d6c4d29-1b1e-40c1-8325-37769b04c40d","4a122a3f-baf7-44ea-84a2-eff41a4f22e386a87236-9025-4fd9-8a56-2fd593a3dc1a","168b453b-fded-4325-b986-952dce406bdd64e12ec3-0151-412c-b82c-8a17784148ad","0329a9cf-d576-4ac5-891d-34f15d7604bf096b5ae8-4b7f-4650-81bb-53183a9b9751","aaeba52d-7d12-4850-8554-de46abc95efc426cb06a-3cbd-4caa-ae2c-ca8f4f83110c","2d7e4fc8-e3e9-4e78-831b-2009370d138cf4448a91-d523-47d3-b4e0-e5b543c329b0","01352b15-2a2c-4530-8273-608810610a8be5e72c25-d30e-43d3-be11-e8120ae6d60a","a5cb04dc-db40-4f09-addf-f8e48469456bf27de913-e91b-4977-8023-865320e4b99d","55b13692-63ae-4528-bebd-0b3227e6dc3949803321-600a-49c1-a9d2-d024ba2d60e2","b8fd9479-3635-466a-aa13-3c149ce0f0c1c9d247ed-06ab-41f7-989b-2fe22e8b748f","7e83bf37-2618-4a38-a6eb-9dc7cb8ff43f1e3d7544-05c6-44bc-9a8e-3adf9f67b33a","0867477f-6221-42e1-967b-a1a040d573cf57b58677-9036-476e-99dc-0ea43acbfddd","fc4a5fd1-3c92-498d-bb6b-859feb16b0f4439407de-21f8-494f-81ab-20839fde6d92","b60059e2-c12d-45dd-a237-f87dd7517fe64c7ef98b-92d9-4586-a308-2ce4ea8d16f1","26de7229-be48-42ff-a61e-1187e8d7314334842943-9a07-4a61-b670-cb5dc98cae1a","69ee8248-7061-4d64-97b6-6ae13b909ce737824c6d-66eb-42bb-a6c6-014e6122ea4b","0c08ff17-2a2d-4093-9233-22ea05dda0e44ac1c042-e9db-44e7-8835-d59a11ba4a78","1848eaa5-52ee-4e14-95a3-89ef5b1b0a6b6ec6a6d9-2fff-4ccf-baa6-8649c88fccbe","7e0b72b6-2a7a-4b94-9a31-fee01da655f2200a10a7-13e2-4bb9-ac9e-8ef9cd15efba","ae3f5dcb-3d00-4bdb-85df-a69992db1ae57276da33-bbf8-4bd3-9523-7394a3523804","db03fda1-78b1-4d5a-9e8c-028dc942ce838163f7ac-114c-4f7c-8e60-18831691920f","ea0d5f29-6651-4465-8667-5f5cba4e13d8bbc37a35-9bdb-4584-9b5c-f6ca36fa9fd1","de46ab22-1180-4b25-b073-8094bd45aaeffdf05058-ff67-4c78-a899-5e558675375f","d60e1437-cd47-4a18-9b40-ad6e04d47c1bd246b74e-abcb-47c0-a0f4-ea1d322ec36f","14575fb6-7c2a-4f43-91a3-630201140483659f4e08-1a90-4b55-b4b6-7b12f856769a","8f2a616d-b099-4fa2-a52e-8413e8b82e416319bb5c-e3f0-427f-8da2-a9d7f7a04d3a","12a979eb-047c-4452-9ded-807f8be59587757ce773-f39b-46bb-946b-a6c5bb2d2d50","d437c97d-3f6f-4b8a-a7bc-373d37be5b9ac47fe2ef-74ce-4cea-bbcb-a5153fd1b331","a5d9454e-a998-4521-af32-25db67c686812385df39-181f-4783-99df-18a346fe0611","fda02baf-793e-4c3b-af94-4f589f04288832ef32ef-5ba8-484f-915d-abc882f4d1e1","ac53f159-23ff-4cae-b59b-a9d746586225f7bcb553-7375-4e00-b71f-9e13624bc9c5","96c6f341-38e1-428b-8959-fcbfb62bf4d25be80651-fd39-467d-9570-632d9c9ed741","9882afbc-a2e1-42b1-b040-d8e94dabbc7c862c6a4d-2312-4739-b0c9-ae0bf1b83615","0ee3fbe3-9a21-45f0-a755-77972ffad95c57a2bfbb-8b64-4477-b815-9302d1331e51","563e09b3-5adb-4f7c-85c0-ea745a747b1c0c1e5c41-636e-4f1e-a69f-8a2293c161f7","8b4c8ba9-4497-4f04-b34c-8eb110a15483a5e2f2bb-798a-4dbd-8413-7cda63f73c9c","480283d3-7813-495c-97b4-39b19251d0b186a40229-48e5-49ef-a098-751dbab74b43","59076788-b210-4089-b898-e7077769561e6509d623-0aba-48f2-8d4b-2b63b0489593","f08f72db-21f7-49b7-b040-2656bf8b365afd56e4ed-c7c6-4ada-883d-49da1f1654a1","6026c2f2-8587-4b3d-b04e-95d267bf7a8562d36ec3-d097-460e-a382-4cad8503ed0f","cfe7223b-482b-414a-9966-45649d43894fe724e023-f726-41ca-8b8e-fd0f3ddea48b","d14f044f-83da-4ad3-a6d2-cea29d286a2dce9dad02-8ce9-49dc-8b1b-6f5d9a0bdf52","f7de6fb9-448c-4a0e-9bce-89520bde12410db55d61-8ece-4576-a2ba-0d7150cc1630","3f815ee5-aa60-405c-83dc-d03ce5e4a4ba5b58778a-3085-4072-9cad-3f53a13989b7","34e4cba7-d931-4197-9e27-735192e9a4d436dffe12-5a41-4b4a-9593-6aed854718c0","3a4996b8-3068-4d02-9eb7-d414b314579f0e31e7e1-475f-4c0c-9a35-7e3f6467a7d2","8b5a3011-126d-4977-a13a-23194a0b863de9c435ee-2f7b-4733-adf0-4710ef2e1ee7","88345403-11cc-4293-8207-d48c2f49ab6ba0c72dd1-2287-4fb3-b5fc-efd27b217e9d","0492f965-3574-43d0-9b45-b22263a0e1a7611f586c-056f-4367-849a-d4e4b79d9d89","9471a194-8cf1-4a36-9e66-949afc55d1889172dbf0-15b9-4269-afed-4bbe4d4aa367","c1095ccd-28dd-49fd-80a8-0584ab687e3baddfc46a-a70e-44a8-a411-ecdeef154073","fa081b5b-d72b-4e84-8aa3-e4647b6fc53004acd972-11df-4ffe-9ab9-9f1c246933ce","30de6dc9-4e9b-4468-94ee-3035e17ffad48177a9b7-493a-4042-aa8d-7b8ac575d2e3","3fb92c1f-9853-4aab-93be-337eddddd9cc372212c6-53d5-42fc-8110-cb631e0fe5e5","89e24703-9c82-4ee3-8b2b-dc3bfb800a08dc36f4a0-a0a7-4ea1-8121-b9bbf10a764e","af09ab64-5208-4d78-8c9f-17be5903247473c5d17f-bdf6-4ae8-92f0-3fbe1c28082e","1ce3d6c6-bdfe-4a7b-9103-6e98219978ef4385a427-df16-4103-90a8-15bc091049f3","86017e9d-0886-44c5-9dca-4aa61ea6fa21e794c633-8f6b-49a3-9a32-136861fba22b","80b4a99d-4447-48d5-8460-226b3036ba1ad4aec2d9-f3f6-497a-926c-889344bd1af3","5bed62af-b8a8-492f-aeea-e2277b83b0401351b75b-2fd2-455b-bf96-540ed5c14fbf","11e7137a-d69d-4cd8-a4e9-0f90517086b1b5afb8a0-51e3-4e7e-8cc5-187649c34690","57319956-d0e2-4c9c-9cae-41621c6e3ab109ff0328-6a4e-480a-9b28-71cd4b1e17ed","93d2cf45-c28a-4b82-afb2-60778c53e617d99a4562-c857-49f7-939c-a22185cc13d4","a059b530-88d7-47cb-91fc-255d0058e80a822d79e3-3009-41af-9132-c2b07ecc2a0b","07845797-32cd-451e-b1d0-9e158f96033e1fbe47fe-b07a-4a29-b2b0-0d7ed1578ec5","d415559e-a483-4637-bd69-1056dd3059ec92e884ba-5eb5-4a00-a9f6-08039cc6ae43","4d41bfdc-9a24-46dd-adae-3bf2cbdf9817baf1eb92-93c1-438c-bf0a-39f62b7f6e2b","7e42866b-25ea-48fb-afd3-3d9d0c73e7ada5c0a765-9cc5-4346-adb6-087e2af4e086","712d1ac6-3533-4289-ae0d-6dac7d0bfe8034d9945a-e0e4-465e-9f3f-d881ac5cafac","fee2c90a-cb83-4dd5-b9b4-860553cc33636e05defc-1dbc-47b1-a56d-c232a27ec55e","cc5afbcb-eff9-41e7-b532-34326e689f0829014c32-5050-4d84-bc10-3d8570329de7","efa45628-6714-412e-a4fa-30b8f00252c6b5758972-b0a2-4a42-8497-e4a8ce49ffa0","6e732626-c594-484f-9b8d-d5d467e1fc1076a200f2-551d-43f5-ae27-aa1d1a044460","e496e187-f510-49ec-bbfe-844841aa67c20ee0a626-dd53-4692-ae25-8578aa6de7ab","6607d747-2966-4871-84bf-1b7b577711fc1a07a62f-2806-4e18-8be9-6ed907dcf630","5c671a6b-8a41-43b5-bfa3-7d7fd480ecff877e6402-e300-4f46-856d-eb85ce78a692","de45367c-e1fb-4e8f-b918-38be665143cb61e26c96-7eeb-4a6a-8bec-ddf83a1224ca","f2edcaf6-ec60-4ac3-aa5a-d42865d5de6b43f67aca-8c7d-4f9d-bbf0-b8b6750ed0f1","4c5fb790-213a-49bd-9b0a-fbc1f90f56fc01bfbf28-656b-4544-b33f-2f8a22c01316","f6b473c5-30e9-4d08-9005-735132bc1b909c7b4ff2-5a9d-439d-98c5-05d55f4afdf9","852874a8-3797-4794-80f9-4900503817addcb2aa37-09fb-47a1-b8d4-6460be3dc20b","1f470b40-ab1d-4f5f-9598-f236e4d19e6fe2e20cb8-0522-47ee-9f3f-33d690050d2a","2d9fa6db-bfef-421d-9090-350f070af089a022aa2f-dfeb-423a-8be8-456e1b133c96","3248e6b9-c299-4292-886b-0fec425afd6cef5488e2-be3d-4d28-a9a2-ed2bb3f444b4","7f8d0ba2-238f-4a59-a7fd-8303dfc6ca03382ecec9-f374-464b-9743-d4ef6b45ee0d","5ac75fba-d10c-49c2-8b89-a99ed8f268d214d4a317-8b83-4118-9c36-3685a2572df6"};
        List<String> eipList = Arrays.asList(eipTodo);
        List<String> hrList = Arrays.asList(hrTodo);

        for(String e:eipList){

            if(hrList.contains(e)){
                System.out.println(e);
            }
        }*/

       /* List<String> station = new ArrayList<>();
        station.add("1");
        station.add("2");
        station.add("3");

        String s =station.stream()
                .collect(Collectors.joining(","));;
        System.out.println(s);

        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add("Date");

        // 使用Iterator来遍历并删除元素
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.equals("Banana")) {
                iterator.remove(); // 删除元素
            }
        }*/

        // 打印结果，检查元素是否已被删除
       // System.out.println(list);
     /*   int a=101;
        int b=10000;
        Double c = new Double(Math.round(a*10000/b)/10000.0);
        System.out.println(c);*/
        CurrentLoginUser user = new CurrentLoginUser("admin", "admin", "系统管理员", "A","","", "A02","","","","","","","241");
        String jwtToken = TokenUtils.createSimpleToken(user);
        System.out.println(jwtToken);
/*
        // 获取前一天的日期
        java.util.Date yesterday = DateUtil.yesterday();
        // 打印结果
        System.out.println("昨天的日期是：" + DateUtil.formatDate( DateUtil.yesterday()));

        System.out.println(DateUtil.formatDate(DateUtil.offsetDay(DateUtil.parseDate("2024-03-11"), 1)));*/

    }


    /**
     * 重要指标-列表
     * @param pageReqDTO 分页参数
     * @return 重要指标列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "重要指标-列表")
    public PageResponse<IndicatorResDTO> page(@RequestParam String dataType,
                                              @RequestParam(required = false) String startDate,
                                              @RequestParam(required = false) String endDate,
                                                    @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(indicatorService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 重要指标-详情
     * @param id 入参数
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "重要指标")
    public DataResponse<IndicatorResDTO> add(@RequestParam String id) {
        return DataResponse.of(indicatorService.detail(id));
    }

    /**
     * 重要指标-新增
     * @param indicatorReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "重要指标-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody IndicatorReqDTO indicatorReqDTO) {
        indicatorService.add(currentLoginUser,indicatorReqDTO);
        return DataResponse.success();
    }

    /**
     * 重要指标-编辑
     * @param indicatorReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "重要指标-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.modify.class})
                               @RequestBody IndicatorReqDTO indicatorReqDTO) {
        indicatorService.modify(currentLoginUser,indicatorReqDTO);
        return DataResponse.success();
    }

    /**
     * 重要指标-删除
     * @param baseIdsEntity 入参数
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "重要指标-删除)")
    public DataResponse<T> delete(@RequestBody  BaseIdsEntity baseIdsEntity) {
        indicatorService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }

}
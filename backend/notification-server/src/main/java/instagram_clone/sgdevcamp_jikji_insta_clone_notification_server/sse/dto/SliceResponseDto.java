package instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.sse.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Slice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SliceResponseDto<D> {
	private int numberOfElements;
	private boolean hasNext;
	private List<D> data;

	public static <E, D> SliceResponseDto create(Slice<E> entity, Function<E, D> makeDto) {
		List<D> dto = convertToDto(entity, makeDto);
		return new SliceResponseDto(entity.getNumberOfElements(), entity.hasNext(), dto);
	}

	private static <E, D> List<D> convertToDto(Slice<E> entity, Function<E, D> makeDto) {
		return entity.getContent().stream()
			.map(e -> makeDto.apply(e))
			.collect(Collectors.toList());
	}
}